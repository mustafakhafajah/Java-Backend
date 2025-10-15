package com.example.demo.controllers;

import com.example.demo.entities.Friendship;
import com.example.demo.reposetories.FriendshipRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/friendships")
public class FriendshipController {

    private final FriendshipRepository friendshipRepository;

    public FriendshipController(FriendshipRepository friendshipRepository) {
        this.friendshipRepository = friendshipRepository;
    }

    // Send a friend request to another user
    @PostMapping("/request")
    public Friendship sendFriendRequest(@RequestBody Friendship friendship) {
        friendship.setStatus("PENDING");
        return friendshipRepository.save(friendship);
    }

    // Retrieve a list of incoming friend requests
    @GetMapping("/requests/incoming/{userId}")
    public List<Friendship> getIncomingRequests(@PathVariable Integer userId) {
        return friendshipRepository.findIncomingRequests(userId);
    }

    // Accept a friend request
    @PutMapping("/accept")
    public ResponseEntity<Void> acceptFriendRequest(@RequestBody Friendship friendship) {
        int rowsUpdated = friendshipRepository.acceptRequest(friendship.getUserOneId(), friendship.getUserTwoId());
        if (rowsUpdated > 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Retrieve the list of friends for a user
    @GetMapping("/friends/{userId}")
    public List<Friendship> getFriends(@PathVariable Integer userId) {
        return friendshipRepository.findFriends(userId);
    }

    // Unfriend a user
    @DeleteMapping("/unfriend")
    public ResponseEntity<Void> unfriendUser(@RequestBody Friendship friendship) {
        int rowsDeleted = friendshipRepository.deleteFriendship(friendship.getUserOneId(), friendship.getUserTwoId());
        if (rowsDeleted > 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}