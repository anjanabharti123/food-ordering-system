package com.foodorder.food_ordering_system.service;

import com.foodorder.food_ordering_system.entity.User;
import com.foodorder.food_ordering_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> updateUser(Long id, User updatedUser) {
        Optional<User> existing = userRepository.findById(id);
        if (existing.isPresent()) {
            User user = existing.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());
            user.setRole(updatedUser.getRole());
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
