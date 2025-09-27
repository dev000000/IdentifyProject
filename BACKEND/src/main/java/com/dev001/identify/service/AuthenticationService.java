package com.dev001.identify.service;

import com.dev001.identify.dto.request.AuthenticationRequest;
import com.dev001.identify.dto.request.RefreshTokenRequest;
import com.dev001.identify.dto.request.RegisterRequest;
import com.dev001.identify.dto.response.AuthenticationResponse;
import com.dev001.identify.dto.response.RefreshTokenResponse;
import com.dev001.identify.dto.response.RegisterResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    RegisterResponse register(RegisterRequest request);
    RefreshTokenResponse refreshToken(RefreshTokenRequest request);

    // this section is for oauth2
//    String generateToken(User user, Boolean isAccessToken);

//    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException, AppException;
//
//    String buildScope(User user);
//
//    void logOut(LogoutRequest request) throws ParseException, JOSEException;
//
//    SignedJWT verifyToken(String token, Boolean isRefresh) throws ParseException, JOSEException;
//

}
