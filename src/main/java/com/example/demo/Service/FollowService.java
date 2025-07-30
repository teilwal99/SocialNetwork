package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ConversationResponseDTO;
import com.example.demo.DTO.FollowRequest;
import com.example.demo.Model.Conversation;
import com.example.demo.Model.Follow;
import com.example.demo.Model.Message;
import com.example.demo.Model.User;
import com.example.demo.Repository.ConversationRepository;
import com.example.demo.Repository.FollowRepository;
import com.example.demo.Repository.MessageRepository;
import com.example.demo.Repository.UserRepository;

@Service
public class FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Autowired
    private UserRepository userRepository;

    

    public String toggleFollow(FollowRequest request) {
        System.out.println("Toggle follow request: " + request.getRequesterId() + " -> " + request.getTargetId());
        Optional<Follow> existing = followRepository.findByRequesterIdAndTargetId(
            request.getRequesterId(), request.getTargetId()
        );

        if (existing.isPresent()) {
            followRepository.delete(existing.get());

            // Decrease counts
            updateFollowCounts(request.getRequesterId(), request.getTargetId(), -1);

            return "Unfollowed";
        } else {
            Follow follow = new Follow();
            follow.setRequesterId(request.getRequesterId());
            follow.setTargetId(request.getTargetId());
            followRepository.save(follow);

            // Increase counts
            updateFollowCounts(request.getRequesterId(), request.getTargetId(), 1);

            return "Followed";
        }
    }

    public Integer isFollowing(Long requesterId, Long targetId) {
        Optional<Follow> follow = followRepository
            .findByRequesterIdAndTargetId(requesterId, targetId);

        if (follow.isPresent()) {
            return follow.get().getStatus();
        }

        Optional<Follow> reverseFollow = followRepository
            .findByRequesterIdAndTargetId(targetId, requesterId);

        if (reverseFollow.isPresent()) {
            return reverseFollow.get().getStatus();
        }

        return null; // or -1 for "no follow"
    }

    private void updateFollowCounts(Long RequesterId, Long TargetId, int delta) {
        userRepository.findById(RequesterId).ifPresent(user -> {
            user.setFollowing(user.getFollowing() + delta);
            userRepository.save(user);
        });
        userRepository.findById(TargetId).ifPresent(user -> {
            user.setFollowers(user.getFollowers() + delta);
            userRepository.save(user);
        });
    }

    
}
