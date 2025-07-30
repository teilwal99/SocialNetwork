package com.example.demo.Model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "follow", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"requester_id", "target_id"})
})
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Requester: the one who sends the follow request
   
    @Column(name = "requester_id")
    private Long requesterId;

    // Target: the user being followed
    @Column(name = "target_id")
    private Long targetId;

    // 0 = pending, 1 = accepted, 2 = rejected (you can adjust)
    @Column(nullable = false)
    private Integer status = 0;

    private LocalDateTime timestamp = LocalDateTime.now();

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

}
