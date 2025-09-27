package com.dev001.identify.controller;

import java.text.ParseException;

import com.dev001.identify.dto.request.*;
import com.dev001.identify.dto.response.*;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.dev001.identify.service.AuthenticationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {

        AuthenticationResponse response = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .code(1000)
                .result(response)
                .build();
    }
    @PostMapping("/register")
    public ApiResponse<RegisterResponse> register(@RequestBody @Valid RegisterRequest request) {

        RegisterResponse response = authenticationService.register(request);
        return ApiResponse.<RegisterResponse>builder()
                .code(1000)
                .result(response)
                .build();
    }
    @PostMapping("/refresh-token")
    public ApiResponse<RefreshTokenResponse> refreshToken(@RequestBody RefreshTokenRequest request)
            {
        RefreshTokenResponse response = authenticationService.refreshToken(request);
        return ApiResponse.<RefreshTokenResponse>builder()
                .code(1000)
                .result(response)
                .build();
    }


    // this section is for oauth2

//    @PostMapping("/introspect")
//    public ApiResponse<IntrospectResponse> login(@RequestBody IntrospectRequest request)
//            throws ParseException, JOSEException {
//
//        IntrospectResponse response = authenticationService.introspect(request);
//        return ApiResponse.<IntrospectResponse>builder()
//                .code(1000)
//                .result(response)
//                .build();
//    }
//
//
//    @PostMapping("/refresh-token")
//    public ApiResponse<RefreshTokenResponse> refreshToken(@RequestBody RefreshTokenRequest request)
//            throws ParseException, JOSEException {
//        RefreshTokenResponse response = authenticationService.refreshToken(request);
//        return ApiResponse.<RefreshTokenResponse>builder()
//                .code(1000)
//                .result(response)
//                .build();
//    }
}
