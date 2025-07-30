package com.example.demo.DTO;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class CommentResponseDTO {
    private String content;
    private Long timestamp;
    private UserDTO user;

    public CommentResponseDTO(String content, LocalDateTime timestamp, UserDTO user) {
        this.content = content;
        this.timestamp = timestamp.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        this.user = user;
    }

    // ✅ Getters and setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    // ✅ Inline UserDTO
    public static class UserDTO {
        private String fullname;
        private String image;

        public UserDTO(String fullname, String image) {
            this.fullname = fullname;
            this.image = image;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
