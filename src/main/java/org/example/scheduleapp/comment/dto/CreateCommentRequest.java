package org.example.scheduleapp.comment.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequest {

    private final String content;

    public CreateCommentRequest(String content) {
        this.content = content;
    }
}
