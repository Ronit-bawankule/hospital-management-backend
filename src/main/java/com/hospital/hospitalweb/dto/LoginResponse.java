package com.hospital.hospitalweb.dto;

public class LoginResponse {

    private String token;
    private Long id;
    private String role;
    private String name;
    private String email;

    public LoginResponse() {
    }

    public LoginResponse(
            String token,
            Long id,
            String role,
            String name,
            String email) {

        this.token = token;
        this.id = id;
        this.role = role;
        this.name = name;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}