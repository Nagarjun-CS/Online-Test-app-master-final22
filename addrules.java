package com.example.nagar.onlinetest;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class addrules extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener,DatePickerDialog.OnDateSetListener{

    EditText editText15;
    EditText editText16;
    EditText editText19;
    EditText editText17;
    EditText editText18;
    int p=0;



    public void publish(View view)
    {
        String name = editText19.getText().toString();
        String code = editText17.getText().toString();

        if (name.isEmpty()) {
            Toast.makeText(this, " Test name is missing ", Toast.LENGTH_SHORT).show();
        }
        if (code.isEmpty()) {
            Toast.makeText(this, " Test code is missing ", Toast.LENGTH_SHORT).show();
        }
        if (!name.isEmpty() && !code.isEmpty())
        {
            Toast.makeText(this, " Rules are added ", Toast.LENGTH_SHORT).show();
            editText19.setText("");
            editText17.setText("");
        }

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,i);
        c.set(Calendar.MONTH,i1);
        c.set(Calendar.DAY_OF_MONTH,i2);

        String date = DateFormat.getDateInstance().format(c.getTime());
        editText18.setText(date);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addrules);



        editText15 = (EditText)findViewById(R.id.editText15);
        editText16 = (EditText)findViewById(R.id.editText16);
        editText19 = (EditText)findViewById(R.id.editText19);
        editText17 = (EditText)findViewById(R.id.editText17);
        editText18 = (EditText)findViewById(R.id.editText18);

        editText15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p=1;
                android.support.v4.app.DialogFragment timePicker  = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(),"Timer Picker");
            }
        });

        editText16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p=0;
                android.support.v4.app.DialogFragment timePicker  = new TimePickerFragment1();
                timePicker.show(getSupportFragmentManager(),"Timer Picker1");
            }
        });

        editText18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.DialogFragment datePicker = new datePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");

            }
        });



    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        if(p==1)
        {
            editText15.setText("Hour : "+  i  + " Minute : " + i1 );
        }
       if(p==0)
        {
            editText16.setText("Hour : "+  i  + " Minute : " + i1 );
        }

    }




}
