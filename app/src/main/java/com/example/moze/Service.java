package com.example.moze;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Service {

    @SerializedName("service_id")
    @Expose
    private Integer service_id;

    @SerializedName("portfolio")
    @Expose
    private String portfolio;

    @SerializedName("occupation")
    @Expose
    private String occupation;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("working_hours")
    @Expose
    private String working_hours;

    @SerializedName("cost")
    @Expose
    private String cost;

    public Service(String portfolio, String occupation, String phone, String location, String working_hours, String cost) {
        this.portfolio = portfolio;
        this.occupation = occupation;
        this.phone = phone;
        this.location = location;
        this.working_hours = working_hours;
        this.cost = cost;
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
}
