package com.example.demo.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.ConversationResponseDTO;
import com.example.demo.DTO.FollowRequest;
import com.example.demo.Model.User;
import com.example.demo.Service.FollowService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/follows")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    // Toggle follow/unfollow
    @PostMapping
    public ResponseEntity<?> toggleFollow(@RequestBody FollowRequest followRequest, HttpServletRequest request) {
        User currentUser = (User) request.getAttribute("currentUser");
        if (currentUser == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        String isNowFollowing = followService.toggleFollow(followRequest);

        Map<String, Object> response = new HashMap<>();
        response.put("isFollowing", isNowFollowing);
        return ResponseEntity.ok(response);
    }


    // Check if current user is following someone
    @GetMapping("/status/{targetId}")
    public ResponseEntity<?> isFollowing(@PathVariable Long targetId, HttpServletRequest request) {
        User currentUser = (User) request.getAttribute("currentUser");
        if (currentUser == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        Integer isFollowing = followService.isFollowing(currentUser.getId(), targetId);
        Map<String, Object> response = new HashMap<>();
        response.put("isFollowing", isFollowing != null ? isFollowing : null);
        return ResponseEntity.ok(response);
    }

    
    
}

