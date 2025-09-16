package org.example.scheduleapp.auth.dto;

import lombok.Getter;

@Getter
public class AuthRequest {

    //@NotEmpty(message = "Name is required.")
    private String name;

    //@NotEmpty(message = "E-mail is required.")
    private String email;

    //@NotEmpty(message = "Password is required.")
    private String password;
}
