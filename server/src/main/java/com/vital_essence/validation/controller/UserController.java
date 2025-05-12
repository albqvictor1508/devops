package com.vital_essence.validation.controller;

import com.vital_essence.validation.dto.ForgotPasswordRequest;
import com.vital_essence.validation.entity.User;
import com.vital_essence.validation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/password")
    public ResponseEntity<?> changePassword(ForgotPasswordRequest request) {
        if(request.getNewPassword().length() < 6) {
            return ResponseEntity.status(400).body("Password length must to be between 6 characters");
        }
        return ResponseEntity.status(200).body(service.changePassword(request));
    }
    @PostMapping("/code/{id}")
    public ResponseEntity<?> sendCode(@PathVariable Long id) {
        Integer code = 1000 + (int) (Math.random() * 9000);
        User u = service.findById(id);
        u.setCode(code);

        //crio uma classe de enviar email com o codigo
        return ResponseEntity.status(200).body("");
    }

    @PostMapping("/code/receive")
    public ResponseEntity<?> receiveCode(@RequestBody Long id, @RequestBody Integer code) {

    }
}
