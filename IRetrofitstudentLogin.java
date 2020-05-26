package com.example.nagar.onlinetest;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by nagar on 22-05-2020.
 */

public interface IRetrofitstudentLogin {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("slogin/")
    Call<studentLoginObject> postRawJSON(@Body JsonObject locationPost);
}
