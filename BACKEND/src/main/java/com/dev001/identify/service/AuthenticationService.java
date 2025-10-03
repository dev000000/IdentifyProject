package com.dev001.identify.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.dev001.identify.dto.request.AuthenticationRequest;
import com.dev001.identify.dto.request.RegisterRequest;
import com.dev001.identify.dto.response.AuthenticationResponse;
import com.dev001.identify.dto.response.RegisterResponse;

public interface AuthenticationService {
    // this section is for localStorage
    //    AuthenticationResponse authenticate(AuthenticationRequest request);
    //    RegisterResponse register(RegisterRequest request);
    //    RefreshTokenResponse refreshToken(RefreshTokenRequest request);

    // this section is for http only cookie
    AuthenticationResponse authenticate(AuthenticationRequest request, HttpServletResponse response);

    RegisterResponse register(RegisterRequest request, HttpServletResponse response);

    void refreshToken(HttpServletRequest request, HttpServletResponse response);
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
