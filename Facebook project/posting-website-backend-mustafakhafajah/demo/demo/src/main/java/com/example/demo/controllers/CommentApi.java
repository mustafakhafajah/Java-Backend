package com.example.demo.controllers;

import com.example.demo.entities.Comment;
import com.example.demo.reposetories.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Correct annotation for a REST controller
@RequestMapping("/posts/{postId}/comments")
public class CommentApi {
    private final CommentRepository commentRepository;

    public CommentApi(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @PostMapping
    public ResponseEntity<Comment> create(@RequestBody Comment comment, @PathVariable Integer postId) {
        if (comment.getContent() == null || comment.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Comment content cannot be empty.");
        }

        comment.setPostId(postId);

        Comment savedComment = commentRepository.save(comment);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Comment> getCommentsForPost(@PathVariable Integer postId) {
        // This assumes you've implemented findByPostId in your repository
        // return commentRepository.findByPostId(postId);
        // Fallback for testing: return all comments
        return commentRepository.findAll();
    }

    @GetMapping("/{commentId}") // Added a new endpoint to get a single comment
    public ResponseEntity<Comment> getCommentById(@PathVariable Integer commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            return new ResponseEntity<>(comment.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PutMapping("/{commentId}")
    public ResponseEntity<Integer> updateComment(@PathVariable Integer postId, @PathVariable Integer commentId, @RequestBody Comment updatedComment) {
        int rowsUpdated = commentRepository.updateContent(commentId, updatedComment.getContent());
        if (rowsUpdated > 0) {
            return new ResponseEntity<>(rowsUpdated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer postId, @PathVariable Integer commentId) {
        int rowsDeleted = commentRepository.deleteById(commentId); // Changed delete to deleteById
        if (rowsDeleted > 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}