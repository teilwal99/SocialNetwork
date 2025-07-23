package com.example.demo.Controller;

import com.example.demo.Model.Reaction;
import com.example.demo.Service.ReactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public List<Reaction> getReactionsByPost(@PathVariable Long postId) {
        return reactionService.getReactionsByPostId(postId);
    }

    @GetMapping("/{id}")
    public Optional<Reaction> getReaction(@PathVariable Long id) {
        return reactionService.getReaction(id);
    }

    @PostMapping
    public Reaction createReaction(@RequestBody Reaction reaction) {
        return reactionService.createOrUpdateReaction(reaction);
    }

    @DeleteMapping("/{id}")
    public void deleteReaction(@PathVariable Long id) {
        reactionService.deleteReaction(id);
    }
}
