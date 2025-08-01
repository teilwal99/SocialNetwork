package com.example.demo.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ConversationResponseDTO;
import com.example.demo.Model.Conversation;
import com.example.demo.Model.Follow;
import com.example.demo.Model.Message;
import com.example.demo.Model.User;
import com.example.demo.Repository.ConversationRepository;
import com.example.demo.Repository.FollowRepository;
import com.example.demo.Repository.MessageRepository;
import com.example.demo.Repository.UserRepository;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    public List<Message> getMessagesBetweenUsers(Long sender, Long receiver) {
        return messageRepository.findBySenderAndReceiver(sender, receiver);
    }

    public List<Message> getInbox(Long receiver) {
        return messageRepository.findByReceiver(receiver);
    }

    public List<Message> getOutbox(Long sender) {
        return messageRepository.findBySender(sender);
    }

    public Message sendMessage(Message message) {
        //log the message content
        System.out.println("Sending message: " + message.getId());
        
        message.setTimestamp(LocalDateTime.now());

        // 1. Check for existing conversation
        Optional<Conversation> existingConversation = conversationRepository
            .findByParticipants(message.getSender(), message.getReceiver());

        Conversation conversation = existingConversation.orElseGet(() -> {
            Conversation newConv = new Conversation();
            newConv.setTimestamp(LocalDateTime.now());

            // 👇 Fetch users by ID
            User sender = userRepository.findById(message.getSender())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

            User receiver = userRepository.findById(message.getReceiver())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

            newConv.setParticipants(new ArrayList<>(Arrays.asList(sender, receiver)));
            return conversationRepository.save(newConv);
        });

        
        Message savedMessage = messageRepository.save(message);

        // 3. Now update lastMessage
        conversation.setLastMessage(savedMessage.getId());
        conversationRepository.save(conversation);

        return savedMessage;
    
    }

    public List<ConversationResponseDTO> getFollowers(Long userId){
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));

        List<Follow> follows = followRepository.findByTargetId(userId);

        List<ConversationResponseDTO> result = new ArrayList<>();

        for (Follow follow: follows){
            User follower = userRepository.findById(follow.getRequesterId()).orElse(null);

            Conversation conversation = conversationRepository
            .findByParticipants(user.getId(), follow.getRequesterId()).orElse(null);

            String lastMessage = null;
            LocalDateTime lastTimestamp = null;

            if (conversation != null) {
                Message last = messageRepository.findById(conversation.getLastMessage()).orElse(null);
                lastMessage = last.getContent();
                lastTimestamp = last.getTimestamp();
                
            }

            ConversationResponseDTO dto = new ConversationResponseDTO();
            dto.setFollowerId(follower.getId());
            dto.setUsername(follower.getUsername());
            dto.setFullname(follower.getFullname());
            dto.setProfilePictureUrl(follower.getProfilePictureUrl());
            dto.setConversationId(conversation != null ? conversation.getId() : null);
            dto.setLastMessage(lastMessage);
            dto.setLastMessageTimestamp(lastTimestamp);
            
            result.add(dto);
        }
        return result;
    }
}
