package org.example.scheduleapp.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.auth.dto.*;
import org.example.scheduleapp.auth.service.AuthService;
import org.example.scheduleapp.common.consts.Const;
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
                .body(AuthResponse.success("Welcome!", signUpResponseDto));
    }

    @PostMapping("/signin") // 로그인
    public ResponseEntity<Void> signIn(@RequestBody SignInRequest request, HttpServletRequest httpServletRequest) {

        SignInResponseDto result = authService.signIn(request);

        HttpSession session = httpServletRequest.getSession();
        session.getAttribute(Const.LOGIN_USER, result.getId());
    }
}
