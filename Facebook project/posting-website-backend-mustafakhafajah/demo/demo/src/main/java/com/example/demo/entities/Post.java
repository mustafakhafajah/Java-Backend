package com.example.demo.entities;

import java.time.LocalDateTime;

public class Post {
    private Integer id;
    private String content;
    private LocalDateTime createdAt;
    private Integer userId;

    // No-argument constructor is correct
    public Post() {
    }

    // Constructor for creating a new Post
    public Post(String content, Integer userId) {
        this.content = content;
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
    }

    // Constructor for retrieving a Post from the database
    public Post(Integer id, String content, LocalDateTime createdAt, Integer userId) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    // Corrected Getters and Setters
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
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}