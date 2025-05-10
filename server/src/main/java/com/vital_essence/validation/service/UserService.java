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

    public boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    public User save(User u) {
        return userRepo.save(u);
    }
}
