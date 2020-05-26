package com.example.nagar.onlinetest;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IRetrofitFacultyLogin {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("facultylogin/")
    Call<FacultyLoginObjc> postRawJSON(@Body JsonObject locationPost);
}
