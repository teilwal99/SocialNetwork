package com.example.demo.Service;

import com.example.demo.DTO.CommentResponseDTO;
import com.example.demo.DTO.ReactionListResponseDTO;
import com.example.demo.DTO.ReactionResponseDTO;
import com.example.demo.Model.Reaction;
import com.example.demo.Model.User;
import com.example.demo.Repository.ReactionRepository;
import com.example.demo.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mustache.MustacheProperties.Reactive;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Reaction> getAllReactions() {
        return reactionRepository.findAll();
    }

    public ReactionListResponseDTO getReactionsByPostId(Long postId) {
        List<Reaction> reactions = reactionRepository.findByPostId(postId);

        List<Object[]> countsRaw = reactionRepository.countReactionsByType(postId);
        Map<String, Integer> counts = new HashMap<>();
        for (Object[] row : countsRaw) {
            String reactionType = (String) row[0];
            Long count = (Long) row[1];
            counts.put(reactionType, count.intValue());
        }

        List<ReactionResponseDTO> reactionDTOs = reactions.stream().map(reaction -> {
            User user = userRepository.findById(reaction.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

            CommentResponseDTO.UserDTO userDTO =
                new CommentResponseDTO.UserDTO(user.getFullname(), user.getProfilePictureUrl());

            return new ReactionResponseDTO(
                reaction.getReactionType(),
                reaction.getTimeStamp(),
                userDTO
            );
        }).collect(Collectors.toList());

        return new ReactionListResponseDTO(counts, reactionDTOs);
    }

    public Optional<Reaction> getReaction(Long id) {
        return reactionRepository.findById(id);
    }

    public Reaction createOrUpdateReaction(Reaction reaction) {
        Optional<Reaction> existing = reactionRepository.findByUserIdAndPostId(reaction.getUserId(), reaction.getPostId());
        if (existing.isPresent()) {
            Reaction r = existing.get();
            String existingType = r.getReactionType();
            if(!existingType.equals(reaction.getReactionType())){
                r.setReactionType(reaction.getReactionType());
                return reactionRepository.save(r);
            } else{
                reactionRepository.deleteById(r.getId());
                return null;
            }
        }
        return reactionRepository.save(reaction);
    }

    public void deleteReaction(Long id) {
        reactionRepository.deleteById(id);
    }
}
