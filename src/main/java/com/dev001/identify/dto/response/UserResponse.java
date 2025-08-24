package com.dev001.identify.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String userName;
    String passWord;
    String firstName;
    String lastName;
    LocalDate dob;
}
