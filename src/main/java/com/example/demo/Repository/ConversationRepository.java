package com.example.demo.Repository;

import com.example.demo.Model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    List<Conversation> findByParticipants_Id(Long userId);

    @Query("""
        SELECT c FROM Conversation c
        JOIN c.participants p1
        JOIN c.participants p2
        WHERE p1.id = :userId1 AND p2.id = :userId2
        AND SIZE(c.participants) = 2
    """)
    Optional<Conversation> findByParticipants(@Param("userId1") Long userId1, @Param("userId2") Long userId2);

}
