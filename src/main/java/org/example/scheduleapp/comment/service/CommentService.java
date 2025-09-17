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

    @Transactional(readOnly = true)
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
}
