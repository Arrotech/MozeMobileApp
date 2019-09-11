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

    @SerializedName("img")
    @Expose
    private String img;

    @SerializedName("cost")
    @Expose
    private String cost;

    public Service(String portfolio, String occupation, String phone, String location, String img, String cost) {
        this.portfolio = portfolio;
        this.occupation = occupation;
        this.phone = phone;
        this.location = location;
        this.img = img;
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

    public String getImg() {
        return img;
    }

    public String getCost() {
        return cost;
    }
}
