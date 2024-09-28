package com.example.meetingschedulersystem.controller;

import com.example.meetingschedulersystem.model.User;
import com.example.meetingschedulersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User userCreated = userService.createUser(user);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }
    @GetMapping("/{user-id}")
    public ResponseEntity<User> getUser(@PathVariable("user-id") UUID userId) {
        User response = userService.getUserById(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/{user-id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("user-id") UUID userId) {
        User response = userService.updateUser(user, userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> response = userService.getAllUsers();
        return new ResponseEntity<>(response, response.isEmpty()?HttpStatus.NO_CONTENT:HttpStatus.OK);
    }
}
