package com.example.nagar.onlinetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class descriptive extends AppCompatActivity {
    EditText editText7;




    public void done(View view)
    {
        String ques = editText7.getText().toString();
        if (ques.isEmpty()) {
            Toast.makeText(this, " Question is missing ", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, " Question is added ", Toast.LENGTH_SHORT).show();
            editText7.setText("");
            Intent intent = new Intent(getApplicationContext(),addtest.class);
            intent.putExtra("ques",ques);
            startActivity(intent);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descriptive);
        editText7 = (EditText)findViewById(R.id.editText7);


    }
}
