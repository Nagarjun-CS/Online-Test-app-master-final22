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

public class studentlogin extends AppCompatActivity {

    ProgressDialog progressDialog;
    EditText studentUSN;
    EditText studentPass;
    String stdusn;

    public void studentregister(View view)
    {
        Intent intent= new Intent(getApplicationContext(),studentregister.class);
        startActivity(intent);
    }

    public void login(View view)
    {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        progressDialog.show();
        /*if(!(studentRepass.getText().toString().equals(studentPass.getText().toString()))){
            Toast.makeText(this, "Password did not match", Toast.LENGTH_SHORT).show();
            studentPass.setText("");
            studentRepass.setText("");
            progressDialog.dismiss();
            return;

        }*/
        if (studentUSN.getText().toString().isEmpty()) {
            progressDialog.dismiss();
            Toast.makeText(this, " Name is missing ", Toast.LENGTH_SHORT).show();

        }
        if (studentPass.getText().toString().isEmpty()) {
            progressDialog.dismiss();
            Toast.makeText(this, " USN is missing ", Toast.LENGTH_SHORT).show();
        }
        /*if (studentClass.getText().toString().isEmpty()) {
            progressDialog.dismiss();
            Toast.makeText(this, " Class is missing ", Toast.LENGTH_SHORT).show();
        }
        if (studentEmail.getText().toString().isEmpty()) {
            progressDialog.dismiss();
            Toast.makeText(this, " Email is missing ", Toast.LENGTH_SHORT).show();
        }*/
        else{

            JsonObject jsonObject = new JsonObject();
            //jsonObject.addProperty("usn", studentUsn.getText().toString());
            //jsonObject.addProperty("name", studentName.getText().toString());
            //jsonObject.addProperty("email",studentEmail.getText().toString());
            jsonObject.addProperty("usn",studentUSN.getText().toString());
            jsonObject.addProperty("pass",studentPass.getText().toString());

            IRetrofitstudentLogin jsonPostService = ServiceGenerator.createService(IRetrofitstudentLogin.class, "http://ec2-13-233-208-238.ap-south-1.compute.amazonaws.com/");
            Call<studentLoginObject> call = jsonPostService.postRawJSON(jsonObject);
            Log.e("call",jsonObject.toString());
            call.enqueue(new Callback<studentLoginObject>() {

                @Override
                public void onResponse(Call<studentLoginObject> call, Response<studentLoginObject> response) {
                    try{
                        progressDialog.dismiss();


                        //Log.e("response-success", response.body().getName());
                        Log.e("response-success", response.body().getStatus());
                        Log.e("response-success", "11111111111111111111111111111111111111111111111111111");
                        if(response.body().getStatus().equals("Not Registered")){
                            //Intent intent = new Intent(getApplicationContext(),studenttest.class);
                            //startActivity(intent);
                            studentPass.setText("");
                            studentUSN.setText("");
                        }
                        if(response.body().getStatus().equals("Incorrect Password")){
                            studentPass.setText("");

                        }

                        if(response.body().getStatus().equals("Success!")){
                            Intent intent = new Intent(getApplicationContext(),student.class);
                            intent.putExtra("usn",studentUSN.getText().toString());
                            startActivity(intent);
                            //studentPass.setText("");
                            //studentUSN.setText("");
                        }


                        else{
                            Toast.makeText(studentlogin.this, "Already Registred", Toast.LENGTH_SHORT).show();
                            studentPass.setText("");
                            studentUSN.setText("");
                            //studentEmail.setText("");
                            //studentUsn.setText("");
                            //studentName.setText("");
                            //studentClass.setText("");

                        }



                    }catch (Exception e){
                        Log.i("eeeeeeeee","5555555555555");

                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<studentLoginObject> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(studentlogin.this, "No Internet!!!", Toast.LENGTH_SHORT).show();
                    Log.e("response-success", "22222222222222222222222222");
                    Log.e("response-failure", call.toString());
                }

            });

        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogin);

        studentUSN = (EditText)findViewById(R.id.editText);
        studentPass = (EditText)findViewById(R.id.editText2);
        stdusn = studentUSN.getText().toString();
        //sUSN.usn=stdusn;
    }
}
