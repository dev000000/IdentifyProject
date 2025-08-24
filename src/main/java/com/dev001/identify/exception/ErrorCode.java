package com.dev001.identify.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized exception"),
    USER_EXISTED(1001, "User already existed"),
    USER_NOT_FOUND(1002, "User not found"),
    USERNAME_INVALID(1003, "Username must be between 8 and 20 characters"),
    PASSWORD_INVALID(1004, "Password must be between 8 and 16 characters");




    private int code;
    private String message;

}
