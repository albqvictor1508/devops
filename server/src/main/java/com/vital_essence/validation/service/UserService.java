package com.vital_essence.validation.service;

import com.vital_essence.validation.dto.ForgotPasswordRequest;
import com.vital_essence.validation.entity.User;
import com.vital_essence.validation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public User changePassword(ForgotPasswordRequest request) {
        User u = userRepo.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User with this email not exists"));
        u.setPassword(request.getNewPassword());
        userRepo.save(u);
        return u;
    }

    public User findById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new BadCredentialsException("USER WITH THIS ID NOT EXISTS"));
    }

    public User save(User u) {
        return userRepo.save(u);
    }
}
