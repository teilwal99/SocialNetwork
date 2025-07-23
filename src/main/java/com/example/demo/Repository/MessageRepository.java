package com.example.demo.Repository;

import com.example.demo.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderAndReceiver(Long sender, Long receiver);
    List<Message> findByReceiver(Long receiver);
    List<Message> findBySender(Long sender);
}
