package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Post;
import com.example.demo.Repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
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
}