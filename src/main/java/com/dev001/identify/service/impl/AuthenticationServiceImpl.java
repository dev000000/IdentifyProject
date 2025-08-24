package com.dev001.identify.service.impl;

import com.dev001.identify.dto.request.AuthenticationRequest;
import com.dev001.identify.dto.response.AuthenticationResponse;
import com.dev001.identify.exception.AppException;
import com.dev001.identify.repository.UserRepository;
import com.dev001.identify.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.dev001.identify.exception.ErrorCode.USER_NOT_FOUND;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findByUserName(request.getUserName()).orElseThrow( () -> new AppException(USER_NOT_FOUND));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean isMatch = passwordEncoder.matches(request.getPassWord(), user.getPassWord());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setAuthenticated(isMatch);
        return authenticationResponse;
    }
}
