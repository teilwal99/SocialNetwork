package com.example.demo.Repository;

import com.example.demo.Model.Reaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    Optional<Reaction> findByUserIdAndPostId(Long userId, Long postId);
    List<Reaction> findByPostId(Long postId);
}