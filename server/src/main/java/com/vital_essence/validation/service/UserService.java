package com.vital_essence.validation.service;

import com.vital_essence.validation.dto.ForgotPasswordRequest;
import com.vital_essence.validation.entity.User;
import com.vital_essence.validation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
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
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User with this id not exist"));
    }

    public User findByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User with this email not exist"));
    }

    public void sendCode(User u) {
        Integer code = 1000 + (int)(Math.random() * 9000);
        u.setCode(code);
        userRepo.save(u);
        //enviar codigo pelo email do viado
    }

    public void receiveCode(User u, Integer code) {
        if(!u.getCode().equals(code)) {
            throw new BadCredentialsException("INVALID CODE");
        }

    }

    public User save(User u) {
        return userRepo.save(u);
    }
}
