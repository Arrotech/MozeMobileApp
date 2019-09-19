package com.example.moze;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServiceInterface {

    @POST("api/v1/auth/register")
    Call<User> registerUser(@Body User user);

    @FormUrlEncoded
    @POST("api/v1/auth/login")
    Call<LoginResponse> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("api/v1/add_services")
    Call<ServiceResponse> getServices();

    @POST("api/v1/add_services")
    Call<Service> addService(@Body Service service);
}
