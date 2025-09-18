package com.dev001.identify.service.impl;

import static com.dev001.identify.exception.ErrorCode.USER_NOT_FOUND;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dev001.identify.dto.request.AuthenticationRequest;
import com.dev001.identify.dto.request.IntrospectRequest;
import com.dev001.identify.dto.request.LogoutRequest;
import com.dev001.identify.dto.request.RefreshTokenRequest;
import com.dev001.identify.dto.response.AuthenticationResponse;
import com.dev001.identify.dto.response.IntrospectResponse;
import com.dev001.identify.entity.invalidatedToken.InvalidatedToken;
import com.dev001.identify.entity.user.User;
import com.dev001.identify.exception.AppException;
import com.dev001.identify.exception.ErrorCode;
import com.dev001.identify.repository.InvalidatedTokenRepository;
import com.dev001.identify.repository.UserRepository;
import com.dev001.identify.service.AuthenticationService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InvalidatedTokenRepository invalidatedTokenRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Value("${jwt.refresh-token-key}")
    protected String REFRESH_TOKEN_KEY;

    @Value("${jwt.access-token-key}")
    protected String ACCESS_TOKEN_KEY;

    @Value("${jwt.access-valid-duration}")
    protected Long ACCESS_VALID_DURATION;

    @Value("${jwt.refresh-valid-duration}")
    protected Long REFRESH_VALID_DURATION;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        //        1. Check if userName exists
        User user = userRepository
                .findByUserName(request.getUserName())
                .orElseThrow(() -> new AppException(USER_NOT_FOUND));
        //        2. Use BCryptPasswordEncoder to check if the password matches
        boolean authenticated = bCryptPasswordEncoder.matches(request.getPassWord(), user.getPassWord());
        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        //        3. Generate token
        String token = generateToken(user, true);
        String refreshToken = generateToken(user, false);
        return AuthenticationResponse.builder().accessToken(token).refreshToken(refreshToken).authenticated(true).build();
    }

    @Override
    public String generateToken(User user, Boolean isAccessToken ) {
        //      1. create header
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        //      2. create jwt claims set for payload
        JWTClaimsSet jwtClaimsSet;
        if(Boolean.TRUE.equals(isAccessToken) ) {
            jwtClaimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getUserName())
                    .issuer("https://github.com/DEV00000001") // don vi phat hanh token
                    .issueTime(new Date())
                    .expirationTime(new Date(
                            Instant.now().plus(ACCESS_VALID_DURATION, ChronoUnit.MILLIS).toEpochMilli()))
                    .claim("scope", buildScope(user))
                    .jwtID(UUID.randomUUID().toString())
                    .build();
        }else {
            jwtClaimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getUserName())
                    .issuer("https://github.com/DEV00000001") // don vi phat hanh token
                    .issueTime(new Date())
                    .expirationTime(new Date(
                            Instant.now().plus(REFRESH_VALID_DURATION, ChronoUnit.MILLIS).toEpochMilli()))
                    .claim("scope", buildScope(user))
                    .jwtID(UUID.randomUUID().toString())
                    .build();
        }

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        //        3. create jwsObject for signing
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        try {
            if(Boolean.TRUE.equals(isAccessToken) ) {
                jwsObject.sign(new MACSigner(ACCESS_TOKEN_KEY.getBytes()));
            }else {
                jwsObject.sign(new MACSigner(REFRESH_TOKEN_KEY.getBytes()));
            }
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Error while generating token", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) {
        boolean isValid = false;
        try {
            verifyToken(request.getToken(), false);
            //          1. if token is valid, then set isValid to true, else throw exception in verifyToken method
            isValid = true;
        } catch (JOSEException | ParseException e) {
            log.error("Error while introspecting token", e);
        }
        return IntrospectResponse.builder().valid(isValid).build();
    }

    @Override
    public String buildScope(User user) {
        //      1. create stringJoiner for building scope string
        StringJoiner stringJoiner = new StringJoiner(" ");
        //      2. check if a user has roles and add roles and permissions to scope string
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                //              3. check if a role has permissions and add permissions to the scope string
                if (!CollectionUtils.isEmpty(role.getPermissions())) {
                    role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
                }
            });
        }
        return stringJoiner.toString();
    }

    @Override
    public void logOut(LogoutRequest request) throws ParseException, JOSEException {
        //      1. verify token, if token is invalid, then throw exception
        var signToken = verifyToken(request.getToken(), true);
        //      2. get jit and expiryTime from token
        String jit = signToken.getJWTClaimsSet().getJWTID();
        Date expirationTime = signToken.getJWTClaimsSet().getExpirationTime();
        //      3. build invalidatedToken entity for adding token in DB
        InvalidatedToken invalidatedToken =
                InvalidatedToken.builder().id(jit).expiryTime(expirationTime).build();
        //      4. save invalidatedToken entity
        invalidatedTokenRepository.save(invalidatedToken);
    }

    @Override
    public SignedJWT verifyToken(String token, Boolean isRefresh) throws ParseException, JOSEException {
        //        1. create JWSVerifier for verifying signature

        JWSVerifier jwsVerifier = isRefresh ? new MACVerifier(REFRESH_TOKEN_KEY.getBytes()) : new MACVerifier(ACCESS_TOKEN_KEY.getBytes());
        //        2. parse token into SignedJWT
        SignedJWT signedJWT = SignedJWT.parse(token);
        //        3. get ExpirationTime and verify signature
        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        boolean verified = signedJWT.verify(jwsVerifier);
        //        4. check if verified is false and token not expired
        if ( !verified && expirationTime.after(new Date()) ) {
        //        4.1 if token is access token , check token is expired , then return error code : 410 GONE . cilent will try refresh token
            if(!isRefresh && expirationTime.after(new Date())) {
                throw new AppException(ErrorCode.ACCESS_TOKEN_EXPIRED);
            }
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        //        5. check if the token is not in the blacklist
        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        return signedJWT;
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException {
        //      1. verify token, if token is invalid, then throw exception
        var signToken = verifyToken(request.getRefreshToken(), true);
        //      2. get userName, jit and expiryTime from token
        String userName = signToken.getJWTClaimsSet().getSubject();
        String jit = signToken.getJWTClaimsSet().getJWTID();
        Date expirationTime = signToken.getJWTClaimsSet().getExpirationTime();
        //      3. build invalidatedToken entity for adding token in DB
        InvalidatedToken invalidatedToken =
                InvalidatedToken.builder().id(jit).expiryTime(expirationTime).build();
        //      4. save invalidatedToken entity
        invalidatedTokenRepository.save(invalidatedToken);
        //      5. Generate token
        User user = userRepository.findByUserName(userName).orElseThrow(() -> new AppException(USER_NOT_FOUND));
        String newAccessToken = generateToken(user, true);
        String newRefreshToken = generateToken(user, false);
        return AuthenticationResponse.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .authenticated(true)
                .build();
    }
}
