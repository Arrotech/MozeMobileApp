package com.example.moze.Services.interfaces;


import com.example.moze.Services.model.ServiceList;
import com.example.moze.UserAccount.Model.LoginResponse;
import com.example.moze.Services.model.Service;
import com.example.moze.Services.model.ServiceResponse;
import com.example.moze.UserAccount.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServiceInterface {

    @POST("api/v1/auth/register")
    Call<User> registerUser(@Body User user);

    @POST("api/v1/auth/login")
    Call<LoginResponse> loginUser(@Body LoginResponse loginResponse);

    @GET("api/v1/add_services/{occupation}")
    Call<ServiceResponse> getServices(
            @Path("occupation") String serviceOccupation
    );

    @DELETE("api/v1/add_services/{service_id}")
    Call<Void> deleteService(@Path("service_id") int service_id);

    @GET("api/v1/add_services/{occupation}/{location}")
    Call<ServiceList> getServicesLocation(
            @Path("occupation") String occupation,
            @Path("location") String location
    );

    @POST("api/v1/add_services")
    Call<Service> addService(@Body Service service);
}
