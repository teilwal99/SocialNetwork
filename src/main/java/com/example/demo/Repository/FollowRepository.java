package com.example.demo.Repository;

import com.example.demo.Model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByRequesterIdAndTargetId(Long requesterId, Long targetId);
}
