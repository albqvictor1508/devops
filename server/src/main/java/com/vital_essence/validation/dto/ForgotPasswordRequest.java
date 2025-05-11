package com.vital_essence.validation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ForgotPasswordRequest {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String newPassword;

    public ForgotPasswordRequest() {
        super();
    }
    public ForgotPasswordRequest(final String email, final String newPassword) {
        this.email = email;
        this.newPassword = newPassword;
    }
}
