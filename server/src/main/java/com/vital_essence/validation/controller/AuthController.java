package com.vital_essence.validation.controller;

import com.vital_essence.validation.dto.AuthRequest;
import com.vital_essence.validation.dto.CreateUserRequest;
import com.vital_essence.validation.dto.CreateUserResponse;
import com.vital_essence.validation.entity.User;
import com.vital_essence.validation.service.UserService;
import com.vital_essence.validation.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
@AllArgsConstructor
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtUtil jwtUtil;
    @Autowired
    private UserService service;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthController(final AuthenticationManager authenticationManager, final UserDetailsService userDetailsService, final JwtUtil jwtUtil, UserService service) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.service = service;
    }
    public AuthController() {}


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
            throw new BadCredentialsException("INVALID CREDENTIALS", e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails, authRequest.isRememberMe());

        return ResponseEntity.status(200).body(new AuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(CreateUserRequest request) {
        if(request.getUsername().length() < 6) {
            throw new BadCredentialsException("Username length must to be greather than 6 characters");
        }
        if(request.getPassword().length() < 6) {
            throw new BadCredentialsException("Password length must to be greather than 6 characters");
        }
        User u = new User();
        u.setUsername(request.getUsername());
        u.setEmail(request.getEmail());
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        u.setPassword(encodedPassword);
        service.save(u);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.status(201).body(new CreateUserResponse(u.getId(), jwt));
    }

    static record AuthenticationResponse(String jwt) {}
}
