package com.dev001.identify.dto.response;

import java.time.LocalDate;

import com.dev001.identify.enums.Role;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterResponse {
    String id;
    String username;
    String password;
    String firstName;
    String lastName;
    LocalDate dob;

    Role role;
    //    this for test
    String accessToken;
    String refreshToken;
}
