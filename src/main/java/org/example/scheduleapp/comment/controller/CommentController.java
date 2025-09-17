package org.example.scheduleapp.comment.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.comment.dto.CreateCommentRequest;
import org.example.scheduleapp.comment.dto.CreateCommentResponse;
import org.example.scheduleapp.comment.service.CommentService;
import org.example.scheduleapp.common.consts.Const;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CreateCommentResponse> create(
            @SessionAttribute(name = Const.LOGIN_USER) Long userId,
            @PathVariable Long scheduleId,
            @RequestBody CreateCommentRequest request
    ) {
        return ResponseEntity.ok(commentService.save(userId, scheduleId, request.getContents()));
    }
}
