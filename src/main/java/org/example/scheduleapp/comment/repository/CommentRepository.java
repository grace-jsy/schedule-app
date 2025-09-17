package org.example.scheduleapp.comment.repository;

import org.example.scheduleapp.comment.entity.Comment;
import org.example.scheduleapp.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllBySchedule(Schedule schedule);
}
