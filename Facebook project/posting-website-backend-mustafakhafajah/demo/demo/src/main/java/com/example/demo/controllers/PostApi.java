package com.example.demo.controllers;

import com.example.demo.entities.Post;
import com.example.demo.reposetories.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/posts")
public class PostApi {
    private final PostRepository postRepository;

    public PostApi(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
        if (post.getContent() == null || post.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Post content cannot be empty.");
        }
        return postRepository.save(post);
    }

    @GetMapping
    public List<Post> viewAllRecentPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Post>> getPostById(@PathVariable int id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            return new ResponseEntity<>(post, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable int id) {
        postRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> update(@PathVariable int id, @RequestBody Post updatedPost) {
        int rowsUpdated = postRepository.updateContent(id, updatedPost.getContent());
        if (rowsUpdated > 0) {
            return new ResponseEntity<>(rowsUpdated, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}