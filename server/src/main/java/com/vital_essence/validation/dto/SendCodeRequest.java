package com.vital_essence.validation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SendCodeRequest {
    @Email
    @NotBlank
    private String email;

    public SendCodeRequest(final String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
