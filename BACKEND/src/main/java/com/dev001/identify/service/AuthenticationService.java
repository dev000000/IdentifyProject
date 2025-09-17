package com.dev001.identify.service;

import java.text.ParseException;

import com.dev001.identify.dto.request.AuthenticationRequest;
import com.dev001.identify.dto.request.IntrospectRequest;
import com.dev001.identify.dto.request.LogoutRequest;
import com.dev001.identify.dto.request.RefreshTokenRequest;
import com.dev001.identify.dto.response.AuthenticationResponse;
import com.dev001.identify.dto.response.IntrospectResponse;
import com.dev001.identify.entity.user.User;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);

    String generateToken(User user, Boolean isAccessToken);

    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;

    String buildScope(User user);

    void logOut(LogoutRequest request) throws ParseException, JOSEException;

    SignedJWT verifyToken(String token, Boolean isRefresh) throws ParseException, JOSEException;

    AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException;
}
