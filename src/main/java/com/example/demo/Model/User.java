package com.example.demo.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;

@Entity
public class User {
    @Id @GeneratedValue
    private Long id;
    private String username;
    private String email;
    private String password;
    private String fullname;
    private String bio;
    @Column(name = "image")
    private String profilePictureUrl;
    private Long followers;
    private Long following;
    private Long posts;
    // Add getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getProfilePictureUrl() { return profilePictureUrl; }
    public void setProfilePictureUrl(String profilePictureUrl) { this.profilePictureUrl = profilePictureUrl; }

    public Long getFollowers() { return followers; }
    public void setFollowers(Long followers) { this.followers = followers; }

    public Long getFollowing() { return following; }
    public void setFollowing(Long following) { this.following = following; }

    public Long getPosts() { return posts; }
    public void setPosts(Long posts) { this.posts = posts; }
}