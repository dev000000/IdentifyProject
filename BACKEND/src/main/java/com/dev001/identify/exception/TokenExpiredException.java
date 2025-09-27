package com.dev001.identify.exception;

import org.springframework.security.core.AuthenticationException;

public class TokenExpiredException extends AuthenticationException {

    public TokenExpiredException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public TokenExpiredException(String msg) {
        super(msg);
    }
}
