package com.example.moze;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServiceInterface {

    @POST("api/v1/auth/register")
    Call<User> registerUser(@Body User user);

    @POST("api/v1/auth/login")
    Call<UserLogin> loginUser(@Body UserLogin user);

    @GET("api/v1/add_services/{occupation}")
    Call<List<Service>> getServices(@Path("occupation") String occupation);

    @POST("api/v1/add_services")
    Call<Service> addService(@Body Service service);
}
