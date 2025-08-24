package com.dev001.identify.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserCreationRequest {

    @Size(min = 8, max = 20, message = "USERNAME_INVALID")
    private String username;
    @Size(min = 8, max = 16, message = "PASSWORD_INVALID")
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;
}
