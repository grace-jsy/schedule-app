package org.example.scheduleapp.schedule.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequestDto {

    private String title;
    private String contents;

    public CreateScheduleRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
