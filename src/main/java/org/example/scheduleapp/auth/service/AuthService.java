package org.example.scheduleapp.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.auth.dto.SignUpResponseDto;
import org.example.scheduleapp.user.entity.User;
import org.example.scheduleapp.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public SignUpResponseDto signUp(String name, String email, String password) {

        User user = new User(name, email, password);
        User savedUser = userRepository.save(user);

        return new SignUpResponseDto(savedUser.getId());
    }
}
