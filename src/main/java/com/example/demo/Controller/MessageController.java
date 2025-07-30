package com.example.demo.Controller;

import com.example.demo.DTO.ConversationResponseDTO;
import com.example.demo.Model.Conversation;
import com.example.demo.Model.Message;
import com.example.demo.Model.User;
import com.example.demo.Repository.ConversationRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public Message sendMessage(@RequestBody Message message) {
        return messageService.sendMessage(message);
    }

    @GetMapping("/inbox/{userId}")
    public List<Message> getInbox(@PathVariable Long userId) {
        return messageService.getInbox(userId);
    }

    @GetMapping("/outbox/{userId}")
    public List<Message> getOutbox(@PathVariable Long userId) {
        return messageService.getOutbox(userId);
    }

    @GetMapping("/conversation")
    public List<Message> getConversation(@RequestParam Long sender, @RequestParam Long receiver) {
        return messageService.getMessagesBetweenUsers(sender, receiver);
    }

    @GetMapping("/followers/{userId}")
    public ResponseEntity<?> getFollowers(@PathVariable Long userId) {
        List<ConversationResponseDTO> convs = messageService.getFollowers(userId);
        return ResponseEntity.ok(convs);
    }
}
