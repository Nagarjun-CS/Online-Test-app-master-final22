package com.example.nagar.onlinetest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class facultyregister extends AppCompatActivity {
    ProgressDialog progressDialog;

    public void faculty(View view)
    {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        progressDialog.show();
        if(!(FacultyReenterPasswd.getText().toString().equals(FacultyPasswrd.getText().toString()))){
            Toast.makeText(this, "Password did not match", Toast.LENGTH_SHORT).show();
            FacultyPasswrd.setText("");
            FacultyReenterPasswd.setText("");
            return;

        }
        else{

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name",FacultyName.getText().toString());
            jsonObject.addProperty("usn", FacultyUSN.getText().toString());
            jsonObject.addProperty("email", FacultyEmail.getText().toString());
            jsonObject.addProperty("pass",FacultyPasswrd.getText().toString());
            IRetrofit jsonPostService = ServiceGenerator.createService(IRetrofit.class, "http://ec2-13-233-208-238.ap-south-1.compute.amazonaws.com/");
            //Call<Objec> call = jsonPostService.postRawJSON(jsonObject);
            Call<Objec> call = jsonPostService.postRawJSON(jsonObject);
            Log.e("call",jsonObject.toString());
            call.enqueue(new Callback<Objec>() {

                @Override
                public void onResponse(Call<Objec> call, Response<Objec> response) {
                    try{
                        progressDialog.dismiss();


                        Log.e("response-success", response.body().getName());
                        Log.e("response-success", response.body().getStatus());
                        Log.e("response-success", "11111111111111111111111111111111111111111111111111111");
                        if(response.body().getStatus().equals("True")){
                            Intent intent = new Intent(getApplicationContext(),faculty.class);
                            startActivity(intent);


                        }
                        else{
                            Toast.makeText(facultyregister.this, "Already Registred", Toast.LENGTH_SHORT).show();
                            FacultyPasswrd.setText("");
                            FacultyReenterPasswd.setText("");
                            FacultyEmail.setText("");
                            FacultyUSN.setText("");

                        }



                    }catch (Exception e){

                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<Objec> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(facultyregister.this, "No Internet!!!", Toast.LENGTH_SHORT).show();
                    Log.e("response-success", "22222222222222222222222222");
                    Log.e("response-failure", call.toString());
                }

            });

        }


    }

    EditText FacultyEmail;
    EditText FacultyUSN;
    EditText FacultyPasswrd;
    EditText FacultyReenterPasswd;
    EditText FacultyName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultyregister);

        FacultyEmail=(EditText)findViewById(R.id.editText3);
        FacultyUSN = (EditText)findViewById(R.id.editText4);
        FacultyPasswrd = (EditText)findViewById(R.id.editText5);
        FacultyReenterPasswd = (EditText)findViewById(R.id.editText6);
        FacultyName = (EditText)findViewById(R.id.editText7);
    }
}
