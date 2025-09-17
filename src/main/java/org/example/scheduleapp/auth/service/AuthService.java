package org.example.scheduleapp.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.auth.dto.SignInRequest;
import org.example.scheduleapp.auth.dto.SignInResponseDto;
import org.example.scheduleapp.auth.dto.SignUpResponseDto;
import org.example.scheduleapp.common.config.PasswordEncoder;
import org.example.scheduleapp.user.entity.User;
import org.example.scheduleapp.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public SignUpResponseDto signUp(String name, String email, String password) {

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(name, email, encodedPassword);
        User savedUser = userRepository.save(user);

        return new SignUpResponseDto(savedUser.getId());
    }

    // 로그인
    public SignInResponseDto signIn(SignInRequest request) {

        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new IllegalStateException("Not found email")
        );

        boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!matches) {
            throw new IllegalCallerException("Incorrect Password");
        }

        return new SignInResponseDto(user.getId());
    }
}
