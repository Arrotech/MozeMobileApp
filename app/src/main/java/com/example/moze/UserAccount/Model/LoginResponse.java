package com.example.moze.UserAccount.Model;

public class LoginResponse {

    private String email;
    private String password;

    public LoginResponse(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
