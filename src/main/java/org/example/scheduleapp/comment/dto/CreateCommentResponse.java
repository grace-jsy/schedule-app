package org.example.scheduleapp.comment.dto;

import lombok.Getter;

@Getter
public class CreateCommentResponse {

    private String contents;

    public CreateCommentResponse(String contents) {
        this.contents = contents;
    }
}
