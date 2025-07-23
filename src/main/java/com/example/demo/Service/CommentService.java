package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Comment;
import com.example.demo.Repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Optional<Comment> updateComment(Long id, Comment comment) {
        return commentRepository.findById(id).map(existingComment -> {
            existingComment.setContent(comment.getContent());
            existingComment.setAuthor(comment.getAuthor());
            existingComment.setTimestamp(comment.getTimestamp());
            return commentRepository.save(existingComment);
        });
    }

    public boolean deleteComment(Long id) {
        if(commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}