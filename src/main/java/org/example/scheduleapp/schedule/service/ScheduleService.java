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

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional // 일정 생성
    public ScheduleResponseDto createPost(String title, String contents, Long userId) {

    User user = userRepository.findById(userId).orElseThrow(
            () -> new EntityNotFoundException("User not found")
    );

        Schedule schedule = new Schedule(user, title, contents);

        Schedule savedPost = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(savedPost.getId(), savedPost.getTitle(), savedPost.getContents(), savedPost.getUser().getUsername());
    }

    @Transactional // 일정 단건 조회
    public ScheduleResponseDto getSchedule(Long id) {

        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("User not found")
        );

        return new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getContents(), schedule.getUser().getUsername());
    }

    @Transactional // 일정 모두 조회
    public List<ScheduleResponseDto> getSchedules() {

        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }
}
