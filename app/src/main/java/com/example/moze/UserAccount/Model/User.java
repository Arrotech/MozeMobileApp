package com.example.moze.UserAccount.Model;

public class User {

    private Integer user_id;
    private String firstname;
    private String lastname;
    private String phone;
    private String username;
    private String email;
    private String password;


    public User(String firstname, String lastname, String phone, String username, String email, String password) {
        this.user_id = user_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
