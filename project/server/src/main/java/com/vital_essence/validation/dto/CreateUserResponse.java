package com.vital_essence.validation.dto;

public class CreateUserResponse {
    private Long id;
    private String jwt;

    public CreateUserResponse(final Long id, final String jwt) {
        this.id = id;
        this.jwt = jwt;
    }

    public Long getId() {
        return id;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(final String jwt) {
        this.jwt = jwt;
    }
}
