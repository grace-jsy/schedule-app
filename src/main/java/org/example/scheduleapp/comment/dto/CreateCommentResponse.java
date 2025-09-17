package org.example.scheduleapp.comment.dto;

import lombok.Getter;

@Getter
public class CreateCommentResponse {

    private String content;

    public CreateCommentResponse(String content) {
        this.content = content;
    }
}
