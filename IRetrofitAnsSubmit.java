package com.example.nagar.onlinetest;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by nagar on 24-05-2020.
 */

public interface IRetrofitAnsSubmit {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("next/")
    Call<ansSubmitObject> postRawJSON(@Body JsonObject locationPost);
}
