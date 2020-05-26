package com.example.nagar.onlinetest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class student extends AppCompatActivity {

    Button enter;
    EditText testCode;
   //public String s;
     String stdusn;
    //sUSN s1;

    int inum;

    ProgressDialog progressDialog;

    public  void enter(View view)
    {
        Intent intent = new Intent(getApplication(),studenttest.class);
        intent.putExtra("usn",stdusn);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        enter = (Button)findViewById(R.id.button);
        //testCode = (EditText)findViewById(R.id.editText11);

        Intent intent = getIntent();
        stdusn = intent.getStringExtra("usn");

        Log.e("sssssssssssssss",stdusn);




    }
}
