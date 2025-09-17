package org.example.scheduleapp.comment.dto;

import lombok.Getter;

@Getter
public class CreateCommentRequest {

    private final String contents;

    public CreateCommentRequest(String contents) {
        this.contents = contents;
    }
}
