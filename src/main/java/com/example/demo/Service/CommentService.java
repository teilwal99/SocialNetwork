package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.CommentResponseDTO;
import com.example.demo.DTO.PostResponseDTO;
import com.example.demo.DTO.ProfileDTO;
import com.example.demo.Model.Comment;
import com.example.demo.Model.Post;
import com.example.demo.Model.User;
import com.example.demo.Repository.CommentRepository;
import com.example.demo.Repository.PostRepository;
import com.example.demo.Repository.UserRepository;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<CommentResponseDTO> getCommentByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        if (comments.isEmpty()) {
            return List.of(); 
        }
        return comments.stream().map(comment -> {
            User user = userRepository.findByUsername(comment.getAuthor())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + comment.getAuthor()));;

            CommentResponseDTO.UserDTO userDTO =
                new CommentResponseDTO.UserDTO(user.getFullname(), user.getProfilePictureUrl());

            return new CommentResponseDTO(
                comment.getContent(),
                comment.getTimestamp(),
                userDTO
            );
        }).collect(Collectors.toList());
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