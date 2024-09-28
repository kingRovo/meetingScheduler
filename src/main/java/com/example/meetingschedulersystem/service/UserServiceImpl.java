package com.example.meetingschedulersystem.service;

import com.example.meetingschedulersystem.model.User;
import com.example.meetingschedulersystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user, UUID userId) {
        getUserById(userId);
        user.setId(userId);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(UUID id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
