package com.example.demo.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Message;
import com.example.demo.Repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

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
        return messageRepository.save(message);
    }

}
