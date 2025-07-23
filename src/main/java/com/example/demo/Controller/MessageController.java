package com.example.demo.Controller;

import com.example.demo.Model.Message;
import com.example.demo.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
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
}
