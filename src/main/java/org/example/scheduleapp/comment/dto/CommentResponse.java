package org.example.scheduleapp.comment.dto;

import lombok.Getter;

@Getter
public class CommentResponse {

    private Long commentId;
    private Long content;

    public CommentResponse(Long commentId, String content) {
        this.commentId = commentId;
        this.content = content;
    }
}
