package com.example.nagar.onlinetest;

/**
 * Created by nagar on 22-05-2020.
 */

public class studentLoginObject {
    String name, status;


    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public studentLoginObject(String name, String status) {
        this.name = name;
        this.status = status;
    }
}
