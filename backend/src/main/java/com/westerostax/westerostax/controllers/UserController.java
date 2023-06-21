package com.westerostax.westerostax.controllers;

import com.westerostax.westerostax.models.User;
import com.westerostax.westerostax.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userRepository.findById(id.intValue())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User updatedUser) {
        User user = userRepository.findById(id.intValue())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID: " + id));
        user.setName(updatedUser.getName());
        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        user.setDistrictId(updatedUser.getDistrictId());
        user.setRegionId(updatedUser.getRegionId());
        user.setLordId(updatedUser.getLordId());
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userRepository.deleteById(id.intValue());
    }
}