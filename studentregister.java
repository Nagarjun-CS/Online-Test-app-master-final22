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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class studentregister extends AppCompatActivity {

    EditText studentName;
    EditText studentUsn;
    EditText studentClass;
    EditText studentEmail;
    EditText studentPass;
    EditText studentRepass;
    Button button9;
    String stdusn;

    ProgressDialog progressDialog;



    public void register(View view)
    {
        Intent intent = new Intent(getApplicationContext(),student.class);
        intent.putExtra("usn",studentUsn.getText().toString());
        startActivity(intent);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        progressDialog.show();
        if(!(studentRepass.getText().toString().equals(studentPass.getText().toString()))){
            Toast.makeText(this, "Password did not match", Toast.LENGTH_SHORT).show();
            studentPass.setText("");
            studentRepass.setText("");
            progressDialog.dismiss();
            return;

        }
        if (studentName.getText().toString().isEmpty()) {
            progressDialog.dismiss();
            Toast.makeText(this, " Name is missing ", Toast.LENGTH_SHORT).show();

        }
        if (studentUsn.getText().toString().isEmpty()) {
            progressDialog.dismiss();
            Toast.makeText(this, " USN is missing ", Toast.LENGTH_SHORT).show();
        }
        if (studentClass.getText().toString().isEmpty()) {
            progressDialog.dismiss();
            Toast.makeText(this, " Class is missing ", Toast.LENGTH_SHORT).show();
        }
        if (studentEmail.getText().toString().isEmpty()) {
            progressDialog.dismiss();
            Toast.makeText(this, " Email is missing ", Toast.LENGTH_SHORT).show();
        }
        else{

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("usn", studentUsn.getText().toString());
            jsonObject.addProperty("name", studentName.getText().toString());
            jsonObject.addProperty("email",studentEmail.getText().toString());
            jsonObject.addProperty("pass",studentPass.getText().toString());
            jsonObject.addProperty("class",studentClass.getText().toString());

            IRetrofitstudentRegister jsonPostService = ServiceGenerator.createService(IRetrofitstudentRegister.class, "http://ec2-13-233-208-238.ap-south-1.compute.amazonaws.com/");
            Call<studentRegisterObject> call = jsonPostService.postRawJSON(jsonObject);
            Log.e("call",jsonObject.toString());
            call.enqueue(new Callback<studentRegisterObject>() {

                @Override
                public void onResponse(Call<studentRegisterObject> call, Response<studentRegisterObject> response) {
                    try{
                        progressDialog.dismiss();


                        //Log.e("response-success", response.body().getName());
                        Log.e("response-success", response.body().getStatus());
                        Log.e("response-success", "11111111111111111111111111111111111111111111111111111");
                        if(response.body().getStatus().equals("True")){
                            Intent intent = new Intent(getApplicationContext(),student.class);
                            intent.putExtra("usn",studentUsn.getText().toString());
                            startActivity(intent);


                        }
                        else{
                            Toast.makeText(studentregister.this, "Already Registred", Toast.LENGTH_SHORT).show();
                            studentPass.setText("");
                            studentRepass.setText("");
                            studentEmail.setText("");
                            studentUsn.setText("");
                            studentName.setText("");
                            studentClass.setText("");

                        }



                    }catch (Exception e){
                        Log.i("eeeeeeeee","5555555555555");

                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<studentRegisterObject> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(studentregister.this, "No Internet!!!", Toast.LENGTH_SHORT).show();
                    Log.e("response-success", "22222222222222222222222222");
                    Log.e("response-failure", call.toString());
                }

            });

        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentregister);

        studentName = (EditText)findViewById(R.id.editText7);
        studentUsn = (EditText)findViewById(R.id.editText4);
        studentClass = (EditText)findViewById(R.id.editText8);
        studentEmail = (EditText)findViewById(R.id.editText3);
        studentPass = (EditText)findViewById(R.id.editText5);
        studentRepass = (EditText)findViewById(R.id.editText6);

        button9 = (Button)findViewById(R.id.button9);


        stdusn = studentUsn.getText().toString();


    }
}
