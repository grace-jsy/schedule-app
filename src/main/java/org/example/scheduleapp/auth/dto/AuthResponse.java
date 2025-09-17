package org.example.scheduleapp.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private String errorCode;

    // 성공 응답 생성
    public static <T> AuthResponse<T> success(String message, T data) {
        return new AuthResponse<>(true, message, data, null);
    }
}
