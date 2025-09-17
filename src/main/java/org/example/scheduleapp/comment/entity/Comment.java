package org.example.scheduleapp.comment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.scheduleapp.common.entity.BaseEntity;
import org.example.scheduleapp.schedule.entity.Schedule;
import org.example.scheduleapp.user.entity.User;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @Column(nullable = false, length = 100)
    private String contents;

    public Comment(User user, Schedule schedule, String contents) {
        this.user = user;
        this.schedule = schedule;
        this.contents = contents;
    }
}
