package com.vital_essence.validation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, unique = true)
    private String username;
    @Email
    @Column(unique = true, length = 120)
    private String email;
    @Column(length = 100)
    private String password;
    private Boolean rememberMe;
    @Column(nullable = true, length = 4)
    private Integer code;

    public User() {
        super();
    }
    public User(final String username, final String email, final String password, final boolean rememberMe) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.rememberMe = rememberMe;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setRememberMe(final boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public Boolean isRememberMe() {
        return rememberMe;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}