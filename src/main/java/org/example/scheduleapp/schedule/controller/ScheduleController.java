package org.example.scheduleapp.schedule.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.schedule.dto.CreateScheduleRequestDto;
import org.example.scheduleapp.schedule.dto.ScheduleResponseDto;
import org.example.scheduleapp.schedule.dto.UpdateScheduleRequestDto;
import org.example.scheduleapp.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    // 일정 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getSchedule(@PathVariable Long id) {

        ScheduleResponseDto scheduleResponseDto = scheduleService.getSchedule(id);

        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    // 일정 전체 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getSchedules() {

        List<ScheduleResponseDto> scheduleResponseDtos = scheduleService.getSchedules();

        return new ResponseEntity<>(scheduleResponseDtos, HttpStatus.OK);
    }

    // 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody UpdateScheduleRequestDto request) {

        ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(id, request.getTitle(), request.getContents());

    }

}
