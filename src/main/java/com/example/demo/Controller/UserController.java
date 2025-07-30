package com.example.demo.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.demo.DTO.FollowRequest;
import com.example.demo.DTO.ProfileDTO;
import com.example.demo.Model.User;
import com.example.demo.Security.JwtUtil;
import com.example.demo.Service.FollowService;
import com.example.demo.Service.UserService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private FollowService followService;
    
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User created = userService.createUser(user);
            return ResponseEntity.ok(created);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating user: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        User currentUser = userService.getUserById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        User currentUser = (User) request.getAttribute("currentUser");
        if (currentUser == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }
        return ResponseEntity.ok(currentUser);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Long id = user.getId();
        Optional<User> existingUser = userService.getUserById(id);
        if (existingUser.isPresent()) {
            userService.updateUser(id, user);
            return ResponseEntity.ok(existingUser.get());

        } else {
            return ResponseEntity.status(404).body("User with id " + id + " not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.ok("User with id " + id + " deleted");
        } else {
            return ResponseEntity.status(404).body("User with id " + id + " not found");
        }
    }

    @PutMapping("/{id}/profile")
    public ResponseEntity<?> updateProfile(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Optional<User> user = userService.getUserById(id);
        if (user.isEmpty()) return ResponseEntity.notFound().build();

        String fullname = body.get("fullname");
        String bio = body.get("bio");

        user.get().setFullname(fullname);
        user.get().setBio(bio);
        userService.updateUser(id, user.get());

        return ResponseEntity.ok("Profile updated");
    }

    @PutMapping("/{id}/avatar")
    public ResponseEntity<?> updateAvatar(@PathVariable Long id, @RequestBody Map<String, String> body) {
        System.out.println("body = " + body);
        Optional<User> user = userService.getUserById(id);
        if (user.isEmpty()) return ResponseEntity.notFound().build();

        String newAvatar = body.get("image");
        user.get().setProfilePictureUrl(newAvatar);
        userService.updateUser(id, user.get());

        return ResponseEntity.ok("Avatar updated");
    }
    
}
