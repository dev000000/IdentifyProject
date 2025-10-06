package com.dev001.identify.configuration;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dev001.identify.exception.TokenExpiredException;
import com.dev001.identify.repository.TokenRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        log.info("secretKey : {}", secretKey);
        // this section is for Bearer Token
        //
        //        // 1. get Header from request (Authorization)
        //        final String authHeader = request.getHeader("Authorization");
        //        final String jwt;
        //        final String userName;
        //        // 2. if header is null or not start with Bearer, then dofilter next filter in filter chain
        //        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        //            filterChain.doFilter(request, response);
        //            return;
        //        }
        //        // 3. get jwt from header
        //        jwt = authHeader.substring("Bearer ".length());

        // this section is for HttpOnly Cookie
        String uri = request.getRequestURI();
        if (uri.contains("/login")
                || uri.contains("/register")
                || uri.contains("/introspect")
                || uri.contains("/refresh-token")) {
            filterChain.doFilter(request, response);
            return;
        }
        String jwt = null;
        String userName;
        if (request.getCookies() != null) {
            for (Cookie c : request.getCookies()) {
                if ("accessToken".equals(c.getName())) {
                    jwt = c.getValue();
                    break;
                }
            }
        }
        if (jwt == null) {
            filterChain.doFilter(request, response);
            return;
        }
        var isTokenExpired = jwtService.isTokenExpired(jwt);
        if (isTokenExpired) {
            request.setAttribute("auth.error.code", "ACCESS_TOKEN_EXPIRED");
            SecurityContextHolder.clearContext();
            throw new TokenExpiredException("Access token expired");
        }
        userName = jwtService.extractUserName(jwt).orElse(null);
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
            var isTokenExistedInDatabase = tokenRepository
                    .findByToken(jwt)
                    .map(token -> !token.isRevoked())
                    .orElse(false);
            if (jwtService.isTokenValid(jwt, userDetails) && isTokenExistedInDatabase) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
