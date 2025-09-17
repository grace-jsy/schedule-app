package org.example.scheduleapp.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignInRequest {

    @Email
    private String email;

    @NotBlank
    private String password;
}
