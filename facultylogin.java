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

public class facultylogin extends AppCompatActivity {
    ProgressDialog progressDialog;


    public void faculty(View view)
    {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        progressDialog.show();

        if (FacultyUsn.getText().toString().isEmpty()) {
            progressDialog.dismiss();
            Toast.makeText(this, " USN is missing ", Toast.LENGTH_SHORT).show();
            return;

        }
        if (FacultyPassword.getText().toString().isEmpty()) {
            progressDialog.dismiss();
            Toast.makeText(this, " Password is missing ", Toast.LENGTH_SHORT).show();
            return;
        }

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("usn", FacultyUsn.getText().toString());
            jsonObject.addProperty("pass", FacultyPassword.getText().toString());
            IRetrofitFacultyLogin jsonPostService = ServiceGenerator.createService(IRetrofitFacultyLogin.class, "http://ec2-13-233-208-238.ap-south-1.compute.amazonaws.com/");
            Call<FacultyLoginObjc> call= jsonPostService.postRawJSON(jsonObject);
            Log.e("call",jsonObject.toString());
            call.enqueue(new Callback<FacultyLoginObjc>() {

                @Override
                public void onResponse(Call<FacultyLoginObjc> call, Response<FacultyLoginObjc> response) {
                    try{
                        progressDialog.dismiss();



                        Log.e("response-success", response.body().getStatus());
                        Log.e("response-success", "11111111111111111111111111111111111111111111111111111");
                        if(response.body().getStatus().equals("Success!")){
                            Intent intent = new Intent(getApplicationContext(),faculty.class);
                            startActivity(intent);


                        }
                        else if(response.body().getStatus().equals("Not Registered!")){
                            Toast.makeText(facultylogin.this, "USN Not Registered!!", Toast.LENGTH_SHORT).show();
                            FacultyPassword.setText("");
                            FacultyUsn.setText("");

                        }
                        else{
                            Toast.makeText(facultylogin.this, "Incorrect passowrd!!", Toast.LENGTH_SHORT).show();
                            FacultyPassword.setText("");

                        }



                    }catch (Exception e){

                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<FacultyLoginObjc> call, Throwable t) {
                    progressDialog.dismiss();

                    Toast.makeText(facultylogin.this, "No Internet!!!", Toast.LENGTH_SHORT).show();
                    Log.e("response-success", "22222222222222222222222222");
                    Log.e("response-failure", call.toString());
                }

            });


    }

    public void register(View view)
    {
        Intent intent = new Intent(getApplicationContext(),facultyregister.class);
        startActivity(intent);
    }

    EditText FacultyUsn;
    EditText FacultyPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultylogin);
        FacultyUsn=(EditText)findViewById(R.id.editText);
        FacultyPassword=(EditText)findViewById(R.id.editText2);


    }
}
