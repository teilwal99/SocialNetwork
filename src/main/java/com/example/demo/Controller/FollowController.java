package com.example.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.FollowRequest;
import com.example.demo.Service.FollowService;

@RestController
@RequestMapping("/follows")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendRequest(@RequestBody FollowRequest requestDTO) {
        return ResponseEntity.ok(followService.sendFollowRequest(requestDTO));
    }

    @PutMapping("/accept/{id}")
    public ResponseEntity<String> acceptRequest(@PathVariable Long id) {
        return ResponseEntity.ok(followService.acceptFollowRequest(id));
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<String> cancelRequest(@PathVariable Long id) {
        return ResponseEntity.ok(followService.cancelFollowRequest(id));
    }
}
