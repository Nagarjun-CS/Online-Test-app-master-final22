package com.example.nagar.onlinetest;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by nagar on 22-05-2020.
 */

public interface IRetrofitstudentRegister {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("sregister/")
    Call<studentRegisterObject> postRawJSON(@Body JsonObject locationPost);
}
