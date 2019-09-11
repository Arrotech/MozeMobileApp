package com.example.moze;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServiceInterface {

    @POST("api/v1/auth/register")
    Call<User> registerUser(@Body User user);

    @GET("api/v1/add_services/{occupation}")
    Call <Main> getServices(@Path("occupation") String occupation);

    @POST("api/v1/add_services")
    Call<Service> addService(@Body Service service);
}
