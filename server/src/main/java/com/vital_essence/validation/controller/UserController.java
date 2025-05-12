package com.vital_essence.validation.controller;

import com.vital_essence.validation.dto.CreateUserResponse;
import com.vital_essence.validation.dto.ForgotPasswordRequest;
import com.vital_essence.validation.entity.User;
import com.vital_essence.validation.security.JwtRequestFilter;
import com.vital_essence.validation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/password")
    public ResponseEntity<?> changePassword(ForgotPasswordRequest request) throws BadCredentialsException  {
        try {
            return ResponseEntity.ok(service.changePassword(request));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("ERROR TO CHANGE PASSWORD: " + e);
        }
    }

    @PostMapping("/code/{id}")
    public ResponseEntity<?> sendCode(@PathVariable Long id) {
        User u = service.find
    }
}
