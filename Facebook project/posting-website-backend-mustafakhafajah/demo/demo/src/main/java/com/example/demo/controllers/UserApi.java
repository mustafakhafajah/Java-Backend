package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.reposetories.UserReposetory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserApi {
    private final UserReposetory userReposetory;

    public UserApi(UserReposetory userReposetory) {
        this.userReposetory = userReposetory;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userReposetory.save(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id){
        Optional<User> user = userReposetory.findById(id);
        if(user.isPresent()){
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<User> findAll(){
        return userReposetory.findAll();
    }
}