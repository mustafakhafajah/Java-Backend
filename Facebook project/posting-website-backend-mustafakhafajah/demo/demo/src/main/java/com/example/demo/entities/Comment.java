package com.example.demo.entities;

import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

public class Comment  {

    @Id
    private Integer id;
    private String content;
    private LocalDateTime createdAt;
    private Integer user_id;
    private Integer postId;

    public Comment() {
        // No-argument constructor
    }

    // Constructor for creating a new comment
    public Comment(String content, Integer userId, Integer postId , LocalDateTime createdAt) {
        this.content = content;
        this.user_id = userId;
        this.postId = postId;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public Integer getUserId() { return user_id; }

    public void setUserId(Integer user_id) { this.user_id = user_id; }

    public Integer getPostId() {
        return postId;
    }
    public void setPostId(Integer postId) {
        this.postId = postId;
    }
}