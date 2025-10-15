package com.example.demo.entities;

import org.springframework.data.annotation.Id;

public class Friendship {

    @Id
    private Integer id;
    private Integer userOneId;
    private Integer userTwoId;
    private String status;

    public Friendship() {}

    public Friendship(Integer userOneId, Integer userTwoId, String status) {
        this.userOneId = userOneId;
        this.userTwoId = userTwoId;
        this.status = status;
    }

    // Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getUserOneId() { return userOneId; }
    public void setUserOneId(Integer userOneId) { this.userOneId = userOneId; }
    public Integer getUserTwoId() { return userTwoId; }
    public void setUserTwoId(Integer userTwoId) { this.userTwoId = userTwoId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}