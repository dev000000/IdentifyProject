package com.dev001.identify.controller;

import java.text.ParseException;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.dev001.identify.dto.request.AuthenticationRequest;
import com.dev001.identify.dto.request.IntrospectRequest;
import com.dev001.identify.dto.request.LogoutRequest;
import com.dev001.identify.dto.request.RefreshTokenRequest;
import com.dev001.identify.dto.response.ApiResponse;
import com.dev001.identify.dto.response.AuthenticationResponse;
import com.dev001.identify.dto.response.IntrospectResponse;
import com.dev001.identify.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {


    private final AuthenticationService authenticationService;

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {

        AuthenticationResponse response = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .code(1000)
                .result(response)
                .build();
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectResponse> login(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {

        IntrospectResponse response = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .code(1000)
                .result(response)
                .build();
    }

    @PostMapping("/logout")
    public ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logOut(request);
        return ApiResponse.<Void>builder().code(1000).build();
    }

    @PostMapping("/refresh-token")
    public ApiResponse<AuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest request)
            throws ParseException, JOSEException {
        AuthenticationResponse response = authenticationService.refreshToken(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .code(1000)
                .result(response)
                .build();
    }
}
