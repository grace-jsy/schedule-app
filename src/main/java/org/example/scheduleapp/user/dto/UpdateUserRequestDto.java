package org.example.scheduleapp.user.dto;

import lombok.Getter;

@Getter
public class UpdateUserRequestDto {

    private String password;

    public UpdateUserRequestDto(String password) {
        this.password = password;
    }
}
