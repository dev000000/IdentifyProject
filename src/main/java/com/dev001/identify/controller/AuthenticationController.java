package com.dev001.identify.controller;

import com.dev001.identify.dto.request.AuthenticationRequest;
import com.dev001.identify.dto.response.ApiResponse;
import com.dev001.identify.dto.response.AuthenticationResponse;
import com.dev001.identify.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1000);
        apiResponse.setData(authenticationService.authenticate(request));
        return apiResponse;
    }
    @GetMapping("/logout")
    public String logout(){
        return "logout";
    }

}
