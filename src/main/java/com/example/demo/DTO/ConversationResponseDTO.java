package com.example.demo.DTO;

import java.time.LocalDateTime;

public class ConversationResponseDTO {
    private Long followerId;
    private String fullname;
    private String username;
    private String profilePictureUrl;

    private Long conversationId;
    private String lastMessage;
    private LocalDateTime lastMessageTimestamp;
    private int unreadCount;

    public Long getFollowerId() { return followerId; }
    public void setFollowerId(Long followerId) { this.followerId = followerId; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getProfilePictureUrl() { return profilePictureUrl; }
    public void setProfilePictureUrl(String profilePictureUrl) { this.profilePictureUrl = profilePictureUrl; }

    public Long getConversationId() { return conversationId; }
    public void setConversationId(Long conversationId) { this.conversationId = conversationId; }

    public String getLastMessage() { return lastMessage; }
    public void setLastMessage(String lastMessage) { this.lastMessage = lastMessage; }

    public LocalDateTime getLastMessageTimestamp() { return lastMessageTimestamp; }
    public void setLastMessageTimestamp(LocalDateTime lastMessageTimestamp) { this.lastMessageTimestamp = lastMessageTimestamp; }

    public int getUnreadCount() { return unreadCount; }
    public void setUnreadCount(int unreadCount) { this.unreadCount = unreadCount; }
}
