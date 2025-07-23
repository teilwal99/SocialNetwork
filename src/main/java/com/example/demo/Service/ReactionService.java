package com.example.demo.Service;

import com.example.demo.Model.Reaction;
import com.example.demo.Repository.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;

    public List<Reaction> getAllReactions() {
        return reactionRepository.findAll();
    }

    public List<Reaction> getReactionsByPostId(Long postId) {
        return reactionRepository.findByPostId(postId);
    }

    public Optional<Reaction> getReaction(Long id) {
        return reactionRepository.findById(id);
    }

    public Reaction createOrUpdateReaction(Reaction reaction) {
        Optional<Reaction> existing = reactionRepository.findByUserIdAndPostId(reaction.getUserId(), reaction.getPostId());
        if (existing.isPresent()) {
            Reaction r = existing.get();
            r.setReactionType(reaction.getReactionType());
            return reactionRepository.save(r);
        }
        return reactionRepository.save(reaction);
    }

    public void deleteReaction(Long id) {
        reactionRepository.deleteById(id);
    }
}
