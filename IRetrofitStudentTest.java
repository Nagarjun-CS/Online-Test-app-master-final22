package com.example.nagar.onlinetest;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by nagar on 23-05-2020.
 */

public interface IRetrofitStudentTest {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("test/")
    Call<List<studentTestObject>> postRawJSON(@Body JsonObject locationPost);
}
