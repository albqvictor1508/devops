package com.vital_essence.validation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ReceiveCodeRequest {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private Integer code;

    public ReceiveCodeRequest(final String email, final Integer code) {
        this.email = email;
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
