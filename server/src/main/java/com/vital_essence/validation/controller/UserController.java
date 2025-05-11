package com.vital_essence.validation.controller;

import com.vital_essence.validation.dto.AuthRequest;
import com.vital_essence.validation.dto.ForgotPasswordRequest;
import com.vital_essence.validation.entity.User;
import com.vital_essence.validation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping
    public void changePassword() {

    }

    public User createUser(User u) {
        if(u.getPassword().length() < 6) {
            throw new RuntimeException("this password must to be more longer");
        }
        if(u.getUsername().length() < 6) {
            throw new RuntimeException("this username must to be more longer");
        }
        return userService.save(u);
    }

    public User changePassword(ForgotPasswordRequest request) {
        return userService.changePassword(request);
    }
}
