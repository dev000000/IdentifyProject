package com.dev001.identify.service.impl;

import com.dev001.identify.configuration.JwtService;
import com.dev001.identify.dto.request.AuthenticationRequest;
import com.dev001.identify.dto.request.RefreshTokenRequest;
import com.dev001.identify.dto.request.RegisterRequest;
import com.dev001.identify.dto.response.AuthenticationResponse;
import com.dev001.identify.dto.response.RefreshTokenResponse;
import com.dev001.identify.dto.response.RegisterResponse;
import com.dev001.identify.entity.token.Token;
import com.dev001.identify.entity.user.User;
import com.dev001.identify.exception.AppException;
import com.dev001.identify.mapper.UserMapper;
import com.dev001.identify.repository.TokenRepository;
import com.dev001.identify.repository.UserRepository;
import com.dev001.identify.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.dev001.identify.enums.Role.USER;
import static com.dev001.identify.enums.TokenType.BEARER;
import static com.dev001.identify.exception.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
//    private final InvalidatedTokenRepository invalidatedTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final UserMapper userMapper;

    @Override
    // this service is check user name and password
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        // 1. Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
        );
        // 2. Check user exist
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new AppException(USER_NOT_FOUND));

        // 3. revoke all tokens of user in database
        revokeAllUserTokens(user, true);
        // 4. generate new token for user
        String accessToken = jwtService.generateToken(user,false);
        String refreshToken = jwtService.generateToken(user, true);
        // 5. save token to database
        saveUserToken(user, accessToken, true);
        saveUserToken(user, refreshToken, false);
        // 6. return response
        return AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .authenticated(true)
                .build();
    }
    private void revokeAllUserTokens(User user, boolean isRevokeRefreshToken) {
        var validUserTokens = isRevokeRefreshToken ? tokenRepository.findAllValidTokensByUser(user.getId()) : tokenRepository.findAllValidAccessTokensByUser(user.getId());
        if(validUserTokens.isEmpty()) {
            return;
        }
        validUserTokens.forEach(token ->
           token.setRevoked(true)
        );
        tokenRepository.saveAll(validUserTokens);
    }
    private void saveUserToken(User user, String token, boolean isAccessToken) {
        var savedToken = Token.builder()
                .user(user)
                .token(token)
                .tokenType(BEARER)
                .isAccessToken(isAccessToken)
                .expiryTime(jwtService.extractExpiration(token))
                .revoked(false)
                .build();
        tokenRepository.save(savedToken);
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {

        // 1. check username existed
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(USER_EXISTED);
        }
        // 2. convert RegisterRequest to User
        User user = userMapper.toUser(request);
        // 3. hash password and set it to user
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(hashedPassword);
        // 4. set roles to user (default role is USER for user register)
        user.setRole(USER);
        // 5. save user to DB
        var savedUser = userRepository.save(user);
        // 6. generate token for user
        String accessToken = jwtService.generateToken(savedUser, false);
        String refreshToken = jwtService.generateToken(savedUser, true);
        // 7. save token to DB
        saveUserToken(savedUser, accessToken, true);
        saveUserToken(savedUser, refreshToken, false);
        // 8. convert user to RegisterResponse and return
        RegisterResponse response = userMapper.toRegisterResponse(user);
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        return response;
    }
    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        // 1. get refresh token from request
        String refreshToken = request.getRefreshToken();
        // 2. extract username from refresh token
        String userName = jwtService.extractUserName(refreshToken).orElse(null);
        // 3. check if username in token is null throw ex
        if(userName == null) {
            throw new AppException(REFRESH_TOKEN_EXPIRED);
        }
        // 4. check if user not exist in db throw ex
        var userDetails = userRepository.findByUsername(userName).orElseThrow(() -> new AppException(REFRESH_TOKEN_EXPIRED));
        // 5. check if token is expired or not valid throw ex
        if(!jwtService.isTokenValid(refreshToken, userDetails)) {
            throw new AppException(REFRESH_TOKEN_EXPIRED);
        }
        // 6. check if token is revoked or not throw ex
        var isTokenRevoked = tokenRepository.findByToken(refreshToken).map(token -> !token.isRevoked()).orElse(false);
        if(!isTokenRevoked) {
            throw new AppException(REFRESH_TOKEN_EXPIRED);
        }
        // 6. revoke all token available of user, then generate new access token for user and save it to DB,
        revokeAllUserTokens(userDetails, false);
        var accessToken = jwtService.generateToken(userDetails, false);
        saveUserToken(userDetails, accessToken, true);
        // 7. return response
        return RefreshTokenResponse.builder()
                .accessToken(accessToken)
                .build();
    }

    // this section is for oauth2
//    @Override
//    public String generateToken(User user, Boolean isAccessToken) {
//        //      1. create header
//        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
//        //      2. create jwt claims set for payload
//        JWTClaimsSet jwtClaimsSet;
//        if (Boolean.TRUE.equals(isAccessToken)) {
//            jwtClaimsSet = new JWTClaimsSet.Builder()
//                    .subject(user.getUserName())
//                    .issuer("https://github.com/DEV00000001") // don vi phat hanh token
//                    .issueTime(new Date())
//                    .expirationTime(new Date(Instant.now()
//                            .plus(ACCESS_VALID_DURATION, ChronoUnit.MILLIS)
//                            .toEpochMilli()))
//                    .claim("scope", buildScope(user))
//                    .jwtID(UUID.randomUUID().toString())
//                    .build();
//        } else {
//            jwtClaimsSet = new JWTClaimsSet.Builder()
//                    .subject(user.getUserName())
//                    .issuer("https://github.com/DEV00000001") // don vi phat hanh token
//                    .issueTime(new Date())
//                    .expirationTime(new Date(Instant.now()
//                            .plus(REFRESH_VALID_DURATION, ChronoUnit.MILLIS)
//                            .toEpochMilli()))
//                    .claim("scope", buildScope(user))
//                    .jwtID(UUID.randomUUID().toString())
//                    .build();
//        }
//
//        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
//        //        3. create jwsObject for signing
//        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
//        try {
//            if (Boolean.TRUE.equals(isAccessToken)) {
//                jwsObject.sign(new MACSigner(ACCESS_TOKEN_KEY.getBytes()));
//            } else {
//                jwsObject.sign(new MACSigner(REFRESH_TOKEN_KEY.getBytes()));
//            }
//            return jwsObject.serialize();
//        } catch (JOSEException e) {
//            log.error("Error while generating token", e);
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException, AppException {
//        boolean isValid = false;
//        try {
//            verifyToken(request.getToken(), false);
//            // 1. if token is valid, then set isValid to true, else throw exception in verifyToken method
//            isValid = true;
//        } catch (AppException e) {
//            // 2. throw exception for decoder know reason (expired vs. unauthenticated)
//            throw e;
//        } catch (JOSEException | ParseException e) {
//            throw new AppException(ErrorCode.UNAUTHENTICATED);
//        }
//        return IntrospectResponse.builder().valid(isValid).build();
//    }
//
//    @Override
//    public String buildScope(User user) {
//        //      1. create stringJoiner for building scope string
//        StringJoiner stringJoiner = new StringJoiner(" ");
//        //      2. check if a user has roles and add roles and permissions to scope string
//        if (!CollectionUtils.isEmpty(user.getRoles())) {
//            user.getRoles().forEach(role -> {
//                stringJoiner.add("ROLE_" + role.getName());
//                //              3. check if a role has permissions and add permissions to the scope string
//                if (!CollectionUtils.isEmpty(role.getPermissions())) {
//                    role.getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
//                }
//            });
//        }
//        return stringJoiner.toString();
//    }
//
//    @Override
//    public void logOut(LogoutRequest request) throws ParseException, JOSEException {
//        //      1. verify token, if token is invalid, then throw exception
//        var signToken = verifyToken(request.getToken(), true);
//        //      2. get jit and expiryTime from token
//        String jit = signToken.getJWTClaimsSet().getJWTID();
//        Date expirationTime = signToken.getJWTClaimsSet().getExpirationTime();
//        //      3. build invalidatedToken entity for adding token in DB
//        InvalidatedToken invalidatedToken =
//                InvalidatedToken.builder().id(jit).expiryTime(expirationTime).build();
//        //      4. save invalidatedToken entity
//        invalidatedTokenRepository.save(invalidatedToken);
//    }
//
//    @Override
//    public SignedJWT verifyToken(String token, Boolean isRefresh) throws ParseException, JOSEException, AppException {
//        // 1. create JWSVerifier for verifying signature
//
//        JWSVerifier jwsVerifier = isRefresh
//                ? new MACVerifier(REFRESH_TOKEN_KEY.getBytes())
//                : new MACVerifier(ACCESS_TOKEN_KEY.getBytes());
//        // 2. parse token into SignedJWT
//        SignedJWT signedJWT = SignedJWT.parse(token);
//        // 3. get ExpirationTime and verify signature
//        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
//        boolean verified = signedJWT.verify(jwsVerifier);
//        // 4. if a token is not valid, throw UNAUTHENTICATED
//        if (!verified) {
//            throw new AppException(ErrorCode.UNAUTHENTICATED);
//        }
//        // 5. if a token is expired and is not a refresh token, throw ACCESS_TOKEN_EXPIRED
//        if (expirationTime.before(new Date())) {
//            if (!isRefresh) {
//                throw new AppException(ErrorCode.ACCESS_TOKEN_EXPIRED);
//            }
//            // 6. else if a token is expired and is a refresh token, throw UNAUTHENTICATED
//            throw new AppException(ErrorCode.UNAUTHENTICATED);
//        }
//
//        // 7. check if the token is not in the blacklist
//        if (invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID())) {
//            throw new AppException(ErrorCode.UNAUTHENTICATED);
//        }
//
//        return signedJWT;
//    }
//
}

