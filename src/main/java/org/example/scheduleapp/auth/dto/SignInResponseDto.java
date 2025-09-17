package org.example.scheduleapp.auth.dto;

import lombok.Getter;

@Getter
public class SignInResponseDto {

    private final Long id;

    public SignInResponseDto(Long id) {
        this.id = id;
    }
}
