package com.example.demo.DTO;

public class NotificationRequest {
    public String type;
    public String data;
    public Long receiverId;
    public Long senderId; // optional

    public Long getReceiverId() {
        return receiverId;
    }
    public String getType() {
        return type;
    }
    public String getData() {
        return data;
    }
    public Long getSenderId() {
        return senderId;
    }
}