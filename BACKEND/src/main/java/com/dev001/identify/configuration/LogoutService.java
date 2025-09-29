package com.dev001.identify.configuration;

import com.dev001.identify.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {
    private final TokenRepository tokenRepository;
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            request.setAttribute("auth.error.code", "LOGOUT_FAIL");
            return;
        }
        jwt = authHeader.substring("Bearer ".length());

        var savedToken = tokenRepository.findByToken(jwt).orElse(null);
        // check if token not exist in database, then return
        if(savedToken == null) {
            request.setAttribute("auth.error.code", "LOGOUT_FAIL");
            return;
        }
        // if token exist in database, then get all tokens of user ( get user in token (already get in db) )
        var validUserTokens = tokenRepository.findAllValidTokensByUser(savedToken.getUser().getId());
        // if list of valid token is empty, then return
        if(validUserTokens.isEmpty()) {
            request.setAttribute("auth.error.code", "LOGOUT_FAIL");
            return;
        }
        // revoke all valid token of user
        validUserTokens.forEach(token ->
           token.setRevoked(true)
        );
        tokenRepository.saveAll(validUserTokens);
    }
}
