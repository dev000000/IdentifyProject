package com.dev001.identify.dto.request;

import com.dev001.identify.validator.DobConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

    @Size(min = 8, max = 20, message = "USERNAME_INVALID")
    String userName;
    @Size(min = 8, max = 16, message = "PASSWORD_INVALID")
    String passWord;
    String firstName;
    String lastName;

    @NotNull(message = "DOB_INVALID")
    @DobConstraint(min = 18)
    LocalDate dob;
}
