package org.example.scheduleapp.comment.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.scheduleapp.comment.dto.CommentResponse;
import org.example.scheduleapp.comment.dto.CreateCommentRequest;
import org.example.scheduleapp.comment.dto.CreateCommentResponse;
import org.example.scheduleapp.comment.entity.Comment;
import org.example.scheduleapp.comment.repository.CommentRepository;
import org.example.scheduleapp.schedule.entity.Schedule;
import org.example.scheduleapp.schedule.repository.ScheduleRepository;
import org.example.scheduleapp.user.entity.User;
import org.example.scheduleapp.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    @Transactional // 댓글 생성
    public CreateCommentResponse save(Long userId, Long scheduleId, String contents) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("Not found user")
        );

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new EntityNotFoundException("Not found schedule")
        );

        Comment comment = new Comment(user, schedule, contents);
        Comment savedComment = commentRepository.save(comment);

        return new CreateCommentResponse(savedComment.getContents());
    }

    @Transactional(readOnly = true) // 댓글 조회
    public List<CommentResponse> getComments(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new EntityNotFoundException("Not found schedule")
        );

        List<Comment> allSchedules = commentRepository.findAllBySchedule(schedule);

        return allSchedules.stream()
                .map(comment -> new CommentResponse(
                        comment.getId(),
                        comment.getContents()
                )).toList();
    }

    @Transactional // 댓글 수정
    public CommentResponse updateComment(Long userId, Long commentId, String content) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("Not found user")
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new EntityNotFoundException("Not found comment")
        );

        if(!comment.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Only the author can edit");
        }

        comment.updateContent(content);

        Comment updateComment = commentRepository.save(comment);

        return new CommentResponse(updateComment.getId(), updateComment.getContents());
    }

    @Transactional // 댓글 삭제
    public void deleteComment(Long userId, Long commentId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("Not found user")
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new EntityNotFoundException("Not found comment")
        );

        if(!comment.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Only the author can edit");
        }

        commentRepository.delete(comment);
    }

}
