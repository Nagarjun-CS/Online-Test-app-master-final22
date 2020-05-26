package com.example.nagar.onlinetest;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IRetrofit {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("fsign/")
    Call<Objec> postRawJSON(@Body JsonObject locationPost);
}
