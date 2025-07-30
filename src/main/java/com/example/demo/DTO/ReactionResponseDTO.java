package com.example.demo.DTO;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import com.example.demo.DTO.CommentResponseDTO.UserDTO;

public class ReactionResponseDTO {
    private String reactionType;
    private Long timestamp;
    private UserDTO user;

    public ReactionResponseDTO(String reactionType, LocalDateTime timestamp, UserDTO user){
        this.reactionType = reactionType;
        this.timestamp = timestamp.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        this.user = user;
    }

    // getter setter
    public String getReactionType() {
        return reactionType;
    }

    public void setReactionType(String reactionType) {
        this.reactionType = reactionType;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

}
