package com.example.demo.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.FollowRequest;
import com.example.demo.Model.Follow;
import com.example.demo.Model.User;
import com.example.demo.Repository.FollowRepository;
import com.example.demo.Repository.UserRepository;

@Service
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public FollowService(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    public String sendFollowRequest(FollowRequest requestDTO) {
        Optional<Follow> existingFollow = followRepository.findByRequesterIdAndTargetId(
                requestDTO.getRequesterId(), requestDTO.getTargetId()
        );
        if (existingFollow.isPresent()) {
            return "Follow request already exists.";
        }

        User requester = userRepository.findById(requestDTO.getRequesterId())
                .orElseThrow(() -> new RuntimeException("Requester not found"));
        User target = userRepository.findById(requestDTO.getTargetId())
                .orElseThrow(() -> new RuntimeException("Target not found"));

        Follow follow = new Follow();
        follow.setRequester(requester);
        follow.setTarget(target);
        follow.setStatus(0); // pending
        followRepository.save(follow);

        return "Follow request sent.";
    }

    public String acceptFollowRequest(Long followId) {
        Follow follow = followRepository.findById(followId)
                .orElseThrow(() -> new RuntimeException("Follow request not found"));
        follow.setStatus(1); // accepted
        followRepository.save(follow);
        return "Follow request accepted.";
    }

    public String cancelFollowRequest(Long followId) {
        Follow follow = followRepository.findById(followId)
                .orElseThrow(() -> new RuntimeException("Follow request not found"));
        follow.setStatus(2); // rejected or cancelled
        followRepository.save(follow);
        return "Follow request cancelled.";
    }
}
