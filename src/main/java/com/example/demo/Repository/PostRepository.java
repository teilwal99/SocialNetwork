package com.example.demo.Repository;

import com.example.demo.Model.Post;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // find posts by username
    List<Post> findByAuthor(String author);
}