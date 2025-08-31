package com.dev001.identify.dto.response;

import com.dev001.identify.entity.role.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserResponse {
    String id;
    String userName;
    String passWord;
    String firstName;
    String lastName;
    LocalDate dob;

    Set<Role> roles;

}
