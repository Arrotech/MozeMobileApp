package com.example.moze.Services.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ServiceList {

    private String message;

    @SerializedName("service")
    private List<Service> servicesLocation;

    private String status;

    public ServiceList(String message, List<Service> servicesLocation, String status) {
        this.message = message;
        this.servicesLocation = servicesLocation;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public List<Service> getServicesLocation() {
        return servicesLocation;
    }

    public String getStatus() {
        return status;
    }
}
