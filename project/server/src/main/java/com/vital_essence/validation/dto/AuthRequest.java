package com.vital_essence.validation.dto;

public class AuthRequest {
    private String username;
    private String password;
    private Boolean rememberMe;

    public AuthRequest() {
        super();
    }

    public AuthRequest(final String username, final String password, final Boolean rememberMe) {
        this.username = username;
        this.password = password;
        this.rememberMe = rememberMe;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public Boolean isRememberMe() {
        return rememberMe;
    }

    public void SetRememberMe(final Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
