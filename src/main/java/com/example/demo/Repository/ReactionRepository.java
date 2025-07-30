package com.example.demo.Repository;

import com.example.demo.Model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    Optional<Reaction> findByUserIdAndPostId(Long userId, Long postId);
    List<Reaction> findByPostId(Long postId);
    int countByPostIdAndReactionType(Long postId, String reactionType);
    boolean existsByPostIdAndUserIdAndReactionType(Long postId, Long userId, String reactionType);
    
    @Query("SELECT r.reactionType, COUNT(r) FROM Reaction r WHERE r.postId = :postId GROUP BY r.reactionType")
    List<Object[]> countReactionsByType(@Param("postId") Long postId);
}