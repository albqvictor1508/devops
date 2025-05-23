package com.vital_essence.validation.service;

import lombok.AllArgsConstructor;
import com.vital_essence.validation.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public UserDetails createUser(User u) {
        User user = userService.save(u);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>()
        );
    }

    public CustomUserDetailsService(final PasswordEncoder passwordEncoder, final UserService userService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userService
                .findByUsername(username);
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(),
                u.getPassword(),
                new ArrayList<>()
        );
    }
}
