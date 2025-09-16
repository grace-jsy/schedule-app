package org.example.scheduleapp.schedule.dto;

import lombok.Getter;

@Getter
public class UpdateScheduleRequestDto {

    private String title;
    private String contents;

    public UpdateScheduleRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
