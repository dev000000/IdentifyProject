package com.dev001.identify.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserCreationRequest {

    @Size(min = 8, max = 20, message = "Username must be between 8 and 20 characters")
    private String username;
    @Size(min = 8, max = 16, message = "Password must be between 8 and 16 characters")
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;
}
