package com.example.demo.Controller;

import com.example.demo.DTO.ReactionListResponseDTO;
import com.example.demo.DTO.ReactionResponseDTO;
import com.example.demo.Model.Reaction;
import com.example.demo.Service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/reactions")
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @GetMapping
    public List<Reaction> getAllReactions() {
        return reactionService.getAllReactions();
    }

    @GetMapping("/post/{postId}")
    public ReactionListResponseDTO getReactionsByPost(@PathVariable Long postId) {
        return reactionService.getReactionsByPostId(postId);
    }

    @GetMapping("/{id}")
    public Optional<Reaction> getReaction(@PathVariable Long id) {
        return reactionService.getReaction(id);
    }

    @PostMapping
    public ResponseEntity<?> createReaction(@RequestBody Reaction reaction) {
        Reaction reactionCreated = reactionService.createOrUpdateReaction(reaction);

        if (reactionCreated == null) {
            // Reaction was removed (unliked)
            return ResponseEntity.ok(Map.of("status", "deleted"));
        }

        // Reaction was created or updated
        return ResponseEntity.ok(reactionCreated);
    }

    @DeleteMapping("/{id}")
    public void deleteReaction(@PathVariable Long id) {
        reactionService.deleteReaction(id);
    }
}
