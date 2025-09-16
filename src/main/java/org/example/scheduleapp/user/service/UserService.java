package org.example.scheduleapp.user.service;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
}
