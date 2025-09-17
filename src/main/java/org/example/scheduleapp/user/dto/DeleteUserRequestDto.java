package org.example.scheduleapp.user.dto;

import lombok.Getter;

@Getter
public class DeleteUserRequestDto {

    private String password;

    public DeleteUserRequestDto(String password) {
        this.password = password;
    }
}
