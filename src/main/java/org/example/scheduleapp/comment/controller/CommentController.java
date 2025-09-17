package org.example.scheduleapp.comment.controller;

import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.comment.dto.CommentResponse;
import org.example.scheduleapp.comment.dto.CommentUpdateRequestDto;
import org.example.scheduleapp.comment.dto.CreateCommentRequest;
import org.example.scheduleapp.comment.dto.CreateCommentResponse;
import org.example.scheduleapp.comment.service.CommentService;
import org.example.scheduleapp.common.consts.Const;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok(commentService.save(userId, scheduleId, request.getContent()));
    }

    // 댓글 조회
    @GetMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(commentService.getComments(scheduleId));
    }

    // 댓글 수정
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @SessionAttribute(name = Const.LOGIN_USER) Long userId,
            @PathVariable Long commentId,
            @RequestBody CommentUpdateRequestDto request) {
        return ResponseEntity.ok(commentService.updateComment(userId, commentId, request.getContent()));
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@SessionAttribute(name = Const.LOGIN_USER) Long userId, @PathVariable Long commentId) {
        commentService.deleteComment(userId, commentId);
        return ResponseEntity.noContent().build();
    }
}
