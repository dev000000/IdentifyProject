package com.dev001.identify.service;

import com.dev001.identify.dto.request.AuthenticationRequest;
import com.dev001.identify.dto.response.AuthenticationResponse;
import com.dev001.identify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);

}
