package com.dev001.identify.dto.response;

import java.time.LocalDate;

import com.dev001.identify.enums.Role;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponse {
    String id;
    String username;
    String password;
    String firstName;
    String lastName;
    LocalDate dob;

    //    Set<Role> roles;
    Role role;
    //    this for test
    String token;
}
