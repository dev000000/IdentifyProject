package com.dev001.identify.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    String passWord;
    String firstName;
    String lastName;
    LocalDate dob;
    List<String> roles;
}
