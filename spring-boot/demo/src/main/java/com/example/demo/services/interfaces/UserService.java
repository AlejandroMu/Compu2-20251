package com.example.demo.services.interfaces;

import com.example.demo.model.User;

public interface UserService {
    public boolean saveUser(User user); // Save a new user to the database
    public User findByUsername(String username); // Find a user by their username
}
