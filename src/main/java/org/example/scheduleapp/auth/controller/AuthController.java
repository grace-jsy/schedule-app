package org.example.scheduleapp.auth.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.auth.dto.AuthRequest;
import org.example.scheduleapp.auth.dto.AuthResponse;
import org.example.scheduleapp.auth.dto.SignUpResponseDto;
import org.example.scheduleapp.auth.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup") // 유저 생성
    public ResponseEntity<AuthResponse<SignUpResponseDto>> signUp(@Valid @RequestBody AuthRequest request) {

        SignUpResponseDto signUpResponseDto = authService.signUp(request.getName(), request.getEmail(), request.getPassword());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AuthResponse.success("Welcome!", signUpResponseDto);

    }
}
