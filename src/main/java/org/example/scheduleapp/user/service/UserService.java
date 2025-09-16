package org.example.scheduleapp.user.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.user.dto.UserResponseDto;
import org.example.scheduleapp.user.entity.User;
import org.example.scheduleapp.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional // 내 정보 조회
    public UserResponseDto getMe(Long id) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Not found user")
        );

        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }
}
