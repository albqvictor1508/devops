package com.vital_essence.validation.controller;

import com.vital_essence.validation.dto.UserDTO;
import com.vital_essence.validation.entity.User;
import com.vital_essence.validation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping
    public User saveUser(@RequestBody User u) {
        return service.save(u);
    }
    
    public void login(UserDTO u) {
        User user = service.findByUsername(u.getUsername());
        if(user == null) {
            throw new RuntimeException("user not exists");
        }
        if(user.isRememberMe()) {
            //setar o jwt com exp 30 pro cara se o remember me for coisado
        }
        //setar jwt com exp do mesmo dia mesmo caso o remember me não for coisado
    }
}
