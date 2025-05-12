package com.vital_essence.validation.controller;

import com.vital_essence.validation.dto.ForgotPasswordRequest;
import com.vital_essence.validation.entity.User;
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
    public ResponseEntity<?> changePassword(@RequestBody ForgotPasswordRequest request) throws BadCredentialsException  {
        try {
            return ResponseEntity.ok(service.changePassword(request));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("ERROR TO CHANGE PASSWORD: " + e);
        }
    }

    @PostMapping("/code/{id}")
    public ResponseEntity<?> sendCode(@RequestBody String email) {
        User u = null;
        try {
            u = service.findByEmail(email);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("ERROR TO FIND USER: " + e);
        }
        service.sendCode(u);
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/code/{id}")
    public ResponseEntity<?> receiveCode(@RequestBody String email, @RequestBody Integer code) {
        User u = null;
        try {
            u = service.findByEmail(email);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("ERROR TO FIND USER: " + e);
        }
        try {
        service.receiveCode(u, code);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(400).body("ERROR TO RECEIVE CODE: " + e);
        }
        return ResponseEntity.status(200).build();
    }
}
