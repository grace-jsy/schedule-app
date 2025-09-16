package org.example.scheduleapp.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.schedule.dto.CreateScheduleRequestDto;
import org.example.scheduleapp.schedule.dto.ScheduleResponseDto;
import org.example.scheduleapp.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성 요청
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createPost(@RequestBody CreateScheduleRequestDto request) {

        Long id = 1L;
        ScheduleResponseDto scheduleResponseDto = scheduleService.createPost(request.getTitle(), request.getContents(), id);

        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleResponseDto);
    }
}
