package com.example.moze.Daraja.network;

import com.example.moze.Daraja.model.LNMExpress;
import com.example.moze.Daraja.model.LNMResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LNMAPI {
    @POST("mpesa/stkpush/v1/processrequest")
    Call<LNMResult> getLNMPesa(@Body LNMExpress lnmExpress);
}
