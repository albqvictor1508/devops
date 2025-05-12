package com.vital_essence.validation.controller;

import com.vital_essence.validation.dto.AuthRequest;
import com.vital_essence.validation.dto.CreateUserRequest;
import com.vital_essence.validation.dto.CreateUserResponse;
import com.vital_essence.validation.entity.User;
import com.vital_essence.validation.service.CustomUserDetailsService;
import com.vital_essence.validation.service.UserService;
import com.vital_essence.validation.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService service;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody  AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            ResponseEntity.status(500).body("INVALID CREDENTIALS: " + e);
        }

        final UserDetails userDetails = customUserDetailsService
                .loadUserByUsername(authRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails, authRequest.isRememberMe());

        return ResponseEntity.status(200).body(new AuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        if(request.getUsername().length() < 6) {
            ResponseEntity.badRequest().body("Username length must to be greather than 6 characters");
        }
        if(request.getPassword().length() < 6) {
            ResponseEntity.badRequest().body("Password length must to be greather than 6 characters");
        }
            User u = new User();

        try {
            u.setUsername(request.getUsername());
            u.setEmail(request.getEmail());
            String encodedPassword = passwordEncoder.encode(request.getPassword());
            u.setPassword(encodedPassword);
        } catch (RuntimeException e) {
            ResponseEntity.status(403).body("ERROR ON CREATE USER: " + e.getMessage());
        }

        String jwt = null;
        try {
            final UserDetails userDetails = customUserDetailsService.createUser(u);
            jwt = jwtUtil.generateToken(userDetails);
        } catch (RuntimeException e) {
            return ResponseEntity.status(403).body("ERROR TO CREATE JWT: %s".formatted(e.getMessage()));
        }
            service.save(u);
            return ResponseEntity.status(201).body(new CreateUserResponse(u.getId(), jwt));
    }

    static record AuthenticationResponse(String jwt) {}
}
