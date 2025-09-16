package org.example.scheduleapp.schedule.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.schedule.dto.ScheduleResponseDto;
import org.example.scheduleapp.schedule.entity.Schedule;
import org.example.scheduleapp.schedule.repository.ScheduleRepository;
import org.example.scheduleapp.user.entity.User;
import org.example.scheduleapp.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public ScheduleResponseDto createPost(String title, String contents, Long userId) {

    User user = userRepository.findById(userId).orElseThrow(
            () -> new EntityNotFoundException("User not found")
    );

        Schedule schedule = new Schedule(user, title, contents);

        Schedule savedPost = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedPost.getId(), savedPost.getTitle(), savedPost.getContents(), savedPost.getUser().getUsername());
    }
}
