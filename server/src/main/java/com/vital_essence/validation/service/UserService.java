package com.vital_essence.validation.service;

import com.vital_essence.validation.entity.User;
import com.vital_essence.validation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public boolean existsById(Long id) {
        return userRepo.existsById(id);
    }

    public User findByUsername(String username) {
        if(!userRepo.existsByUsername(username)) return null;
        return userRepo.findByUsername(username).orElseThrow(() -> new RuntimeException("user not founded"));
    }

    public User save(User u) {
        return userRepo.save(u);
    }
}
