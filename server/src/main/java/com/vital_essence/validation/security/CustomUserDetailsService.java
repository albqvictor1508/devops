package com.vital_essence.validation.security;

import com.vital_essence.validation.repository.UserRepository;
import com.vital_essence.validation.service.UserService;
import lombok.AllArgsConstructor;
import com.vital_essence.validation.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
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
