package com.example.demo.Service;

import com.example.demo.DTO.ConversationDTO;
import com.example.demo.Model.Conversation;
import com.example.demo.Model.Message;
import com.example.demo.Model.User;
import com.example.demo.Repository.ConversationRepository;
import com.example.demo.Repository.MessageRepository;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConversationService {

    @Autowired
    private ConversationRepository conversationRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    public Conversation createConversation(List<Long> userIds) {
        List<User> users = userRepository.findAllById(userIds);
        Conversation convo = new Conversation();
        convo.setParticipants(users);
        return conversationRepository.save(convo);
    }

    public List<ConversationDTO> getUserConversations(Long userId) {
        List<Conversation> conversations = conversationRepository.findByParticipants_Id(userId);
        List<ConversationDTO> result = new ArrayList<>();

        for (Conversation convo : conversations) {
            String lastMessageContent = null;
            if (convo.getLastMessage() != null) {
                Optional<Message> message = messageRepository.findById(convo.getLastMessage());
                lastMessageContent = message.map(Message::getContent).orElse(null);
            }
            result.add(new ConversationDTO(
                convo.getId(),
                lastMessageContent,
                convo.getTimestamp(),
                convo.getParticipants()
            ));
        }

        return result;
    }

}
