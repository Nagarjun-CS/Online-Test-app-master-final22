package com.example.nagar.onlinetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class loginpage extends AppCompatActivity {

    public void faculty(View view)
    {
        Intent intent = new Intent(getApplicationContext(),facultylogin.class);
        startActivity(intent);
    }
    public void student(View view)
    {
        Intent intent = new Intent(getApplicationContext(),studentlogin.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
    }
}
