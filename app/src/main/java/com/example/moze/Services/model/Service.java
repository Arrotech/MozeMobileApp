package com.example.moze.Services.model;

public class Service {

    private Integer service_id;

    private String name;

    private String business_name;

    private String portfolio;

    private String occupation;

    private String social_link;

    private String description;

    private String phone;

    private String location;

    private String working_hours;

    private String cost;

    public Service(String name, String business_name, String portfolio, String occupation, String social_link, String description, String phone, String location, String working_hours, String cost) {
        this.name = name;
        this.business_name = business_name;
        this.portfolio = portfolio;
        this.occupation = occupation;
        this.social_link = social_link;
        this.description = description;
        this.phone = phone;
        this.location = location;
        this.working_hours = working_hours;
        this.cost = cost;
    }

    public String getSocial_link() {
        return social_link;
    }

    public Integer getService_id() {
        return service_id;
    }

    public String getPortfolio() {
        return portfolio;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    public String getWorking_hours() {
        return working_hours;
    }

    public String getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public String getDescription() {
        return description;
    }
}
