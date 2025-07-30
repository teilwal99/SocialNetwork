package com.example.demo.Controller;

import com.example.demo.DTO.ConversationDTO;
import com.example.demo.Model.Conversation;
import com.example.demo.Service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conversations")
public class ConversationController {

    @Autowired
    private ConversationService conversationService;

    @GetMapping("/user/{userId}")
    public List<ConversationDTO> getUserConversations(@PathVariable Long userId) {
        return conversationService.getUserConversations(userId);
    }
}
