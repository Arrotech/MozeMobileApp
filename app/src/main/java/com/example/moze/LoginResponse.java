package com.example.moze;

public class LoginResponse {

    private String message;
    private String refresh_token;
    private  String status;
    private String token;
    private  User user;

    public LoginResponse(String message, String refresh_token, String status, String token, User user) {
        this.message = message;
        this.refresh_token = refresh_token;
        this.status = status;
        this.token = token;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public String getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}
