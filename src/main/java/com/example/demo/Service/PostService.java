package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.PostResponseDTO;
import com.example.demo.Model.Post;
import com.example.demo.Model.User;
import com.example.demo.Repository.CommentRepository;
import com.example.demo.Repository.PostRepository;
import com.example.demo.Repository.ReactionRepository;
import com.example.demo.Repository.UserRepository;

import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReactionRepository reactionRepository;
    @Autowired
    private CommentRepository commentRepository;
    
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public List<PostResponseDTO> getAllPosts(Long currentUserId) {
        List<Post> posts = postRepository.findAll();

        return posts.stream().map(post -> {
            int likeCount = reactionRepository.countByPostIdAndReactionType(post.getId(), "like");
            int commentCount = commentRepository.countByPostId(post.getId());

            boolean isLiked = reactionRepository.existsByPostIdAndUserIdAndReactionType(post.getId(), currentUserId, "like");

            User author = userRepository.findByUsername(post.getAuthor())
                .orElseThrow(() -> new RuntimeException("Author not found"));

            return new PostResponseDTO(
                String.valueOf(post.getId()),
                post.getMedia(),
                post.getContent(),
                commentCount,
                post.getTimestamp().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
                isLiked,
                likeCount,
                new PostResponseDTO.AuthorDTO(
                    String.valueOf(author.getId()),
                    author.getUsername(),
                    author.getProfilePictureUrl()
                )
            );
        }).collect(Collectors.toList());
    }


    public Optional<Post> updatePost(Long id, Post post) {
        return postRepository.findById(id).map(existingPost -> {
            existingPost.setContent(post.getContent());
            existingPost.setMedia(post.getMedia());
            existingPost.setAuthor(post.getAuthor());
            existingPost.setTimestamp(post.getTimestamp());
            return postRepository.save(existingPost);
        });
    }

    public boolean deletePost(Long id) {
        if(postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<PostResponseDTO> getPostsByUsername(String username) {
        List<Post> posts = postRepository.findByAuthor(username);
        if (posts.isEmpty()) {
            return List.of(); 
        }
        return posts.stream().map(post -> {
            User author = userRepository.findByUsername(post.getAuthor())
                .orElseThrow(() -> new RuntimeException("Author not found"));

            int likeCount = reactionRepository.countByPostIdAndReactionType(post.getId(), "like");
            int commentCount = commentRepository.countByPostId(post.getId());

            boolean isLiked = reactionRepository.existsByPostIdAndUserIdAndReactionType(post.getId(), author.getId(), "like");

            

            return new PostResponseDTO(
                String.valueOf(post.getId()),
                post.getMedia(),
                post.getContent(),
                commentCount,
                post.getTimestamp().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
                isLiked,
                likeCount,
                new PostResponseDTO.AuthorDTO(
                    String.valueOf(author.getId()),
                    author.getUsername(),
                    author.getProfilePictureUrl()
                )
            );
        }).collect(Collectors.toList());
    }
}