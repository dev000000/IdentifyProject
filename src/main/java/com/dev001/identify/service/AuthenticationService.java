package com.dev001.identify.service;

import com.dev001.identify.dto.request.AuthenticationRequest;
import com.dev001.identify.dto.request.IntrospectRequest;
import com.dev001.identify.dto.response.AuthenticationResponse;
import com.dev001.identify.dto.response.IntrospectResponse;
import com.dev001.identify.entity.user.User;
import com.dev001.identify.repository.UserRepository;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    String generateToken(User user);
    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
    String buildScope(User user);

}
