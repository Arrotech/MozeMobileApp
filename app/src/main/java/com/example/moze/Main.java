package com.example.moze;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("service")
    @Expose
    private Service service;

    public Main(String message, Service service) {
        this.message = message;
        this.service = service;
    }

    public String getMessage() {
        return message;
    }

    public Service getService() {
        return service;
    }
}
