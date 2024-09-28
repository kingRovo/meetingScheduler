package com.example.meetingschedulersystem.service;

import com.example.meetingschedulersystem.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User createUser(User user);
    User updateUser(User user, UUID userId);
    void deleteUser(UUID id);
    User getUserById(UUID id);
    List<User> getAllUsers();
}
