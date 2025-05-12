package com.vital_essence.validation.dto;

public class ForgotPasswordRequest {
    private String newPassword;
    private String email;

    public ForgotPasswordRequest(final String newPassword, final String email) {
        this.newPassword = newPassword;
        this.email = email;
    }

    public ForgotPasswordRequest() {}

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
