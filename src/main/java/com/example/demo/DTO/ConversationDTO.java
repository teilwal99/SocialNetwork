package com.example.demo.DTO;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.Model.User;

public class ConversationDTO {
    private Long id;
    private String lastMessageContent;
    private LocalDateTime lastMessageTimestamp;
    private List<User> participants;

    // constructor
    public ConversationDTO(Long id, String lastMessageContent, LocalDateTime lastMessageTimestamp, List<User> participants) {
        this.id = id;
        this.lastMessageContent = lastMessageContent;
        this.lastMessageTimestamp = lastMessageTimestamp;
        this.participants = participants;
    }
    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLastMessageContent() {
        return lastMessageContent;
    }
    public void setLastMessageContent(String lastMessageContent) {
        this.lastMessageContent = lastMessageContent;
    }
    public LocalDateTime getLastMessageTimestamp() {
        return lastMessageTimestamp;
    }
    public void setLastMessageTimestamp(LocalDateTime lastMessageTimestamp) {
        this.lastMessageTimestamp = lastMessageTimestamp;
    }
    public List<User> getParticipants() {
        return participants;
    }
    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }
}
