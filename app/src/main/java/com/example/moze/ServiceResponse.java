package com.example.moze;

import java.util.List;

public class ServiceResponse {

    private String message;
    private List<Service> services;
    private String status;

    public ServiceResponse(String message, List<Service> services, String status) {
        this.message = message;
        this.services = services;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public List<Service> getServices() {
        return services;
    }

    public String getStatus() {
        return status;
    }
}
