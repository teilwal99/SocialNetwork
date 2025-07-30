package com.example.demo.Controller;

import com.example.demo.DTO.PostResponseDTO;
import com.example.demo.Model.Post;
import com.example.demo.Model.User;
import com.example.demo.Service.PostService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createdPost = postService.createPost(post);
        return ResponseEntity.ok(createdPost);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPosts(HttpServletRequest request) {
        User currentUser = (User) request.getAttribute("currentUser");
        if (currentUser == null) {
            return ResponseEntity.status(401).body(null); // Unauthorized if no user is set
        }
        List<PostResponseDTO> posts = postService.getAllPosts(currentUser.getId());
        if(posts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        return postService.updatePost(id, post)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        if (postService.deletePost(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<PostResponseDTO>> getPostsByUsername(@PathVariable String username) {
        List<PostResponseDTO> posts = postService.getPostsByUsername(username);
        
        return ResponseEntity.ok(posts);
    }
}