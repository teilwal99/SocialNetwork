package com.example.demo.Service;

import com.example.demo.Model.Conversation;
import com.example.demo.Model.User;
import com.example.demo.Repository.ConversationRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;

    @Autowired
    private UserRepository userRepository;

    public Conversation createConversation(List<Long> userIds) {
        List<User> users = userRepository.findAllById(userIds);
        Conversation convo = new Conversation();
        convo.setParticipants(users);
        return conversationRepository.save(convo);
    }

    public List<Conversation> getUserConversations(Long userId) {
        return conversationRepository.findByParticipants_Id(userId);
    }

}
