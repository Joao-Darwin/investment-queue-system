package com.producer.services;

import com.producer.models.User;
import com.producer.repositores.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return this.userRepository.save(user);
    }

    public User findById(UUID id) {
        return this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found. Searched ID: " + id));
    }

    public User update(UUID id, User user) {
        User userToUpdate = this.findById(id);

        userToUpdate.setName(user.getName());
        userToUpdate.setBalance(user.getBalance());

        return this.userRepository.save(userToUpdate);
    }

    public void delete(UUID id) {
        User userToDelete = this.findById(id);

        this.userRepository.delete(userToDelete);
    }
}
