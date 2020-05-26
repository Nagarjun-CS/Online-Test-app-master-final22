package com.example.nagar.onlinetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class addMcq extends AppCompatActivity {

    EditText editText8;
    EditText editText9;
    EditText editText10;
    EditText editText14;
    EditText editText13;
    EditText editText12;
    Button button7;
    Button button6;
    public String QuesBuffer="";



    public void done(View view)
    {
            String ques = editText8.getText().toString();
            String optA = editText9.getText().toString();
            String optB = editText10.getText().toString();
            String optC = editText12.getText().toString();
            String optD = editText14.getText().toString();
            String ans = editText13.getText().toString();

            if (ques.isEmpty()) {
                Toast.makeText(this, " Question is missing ", Toast.LENGTH_SHORT).show();
            }
            if (optA.isEmpty()) {
                Toast.makeText(this, " Option A is missing ", Toast.LENGTH_SHORT).show();
            }
            if (optB.isEmpty()) {
                Toast.makeText(this, " Option B is missing ", Toast.LENGTH_SHORT).show();
            }
            if (optC.isEmpty()) {
                Toast.makeText(this, " Option C is missing ", Toast.LENGTH_SHORT).show();
            }
            if (optD.isEmpty()) {
                Toast.makeText(this, " Option D is missing ", Toast.LENGTH_SHORT).show();
            }
            if (ans.isEmpty()) {
                Toast.makeText(this, " Correct answer is missing ", Toast.LENGTH_SHORT).show();
            }
            if (!ques.isEmpty() && !optA.isEmpty() && !optB.isEmpty() && !optC.isEmpty() && !optD.isEmpty() && !ans.isEmpty()){
                Toast.makeText(this, " Question is added ", Toast.LENGTH_SHORT).show();
                QuesBuffer=QuesBuffer+ques+"$#$"+optA+",,,"+optB+",,,"+optC+",,,"+optD+",,,"+ans+",,~";

                editText8.setText("");
                editText9.setText("");
                editText10.setText("");
                editText12.setText("");
                editText14.setText("");
                editText13.setText("");

                Intent intent = new Intent(getApplicationContext(),addtest.class);
                intent.putExtra("ques",ques);
                intent.putExtra("optA",optA);
                intent.putExtra("optB",optB);
                intent.putExtra("optC",optC);
                intent.putExtra("optD",optD);
                intent.putExtra("ans",ans);
                startActivity(intent);

            }





    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addquestion);
        editText8 = (EditText)findViewById(R.id.editText8);
        editText9 = (EditText)findViewById(R.id.editText9);
        editText10 = (EditText)findViewById(R.id.editText10);
        editText14 = (EditText)findViewById(R.id.editText14);
        editText12 = (EditText)findViewById(R.id.editText12);
        editText13 = (EditText)findViewById(R.id.editText13);
        button7 = (Button)findViewById(R.id.button7);


    }
}
