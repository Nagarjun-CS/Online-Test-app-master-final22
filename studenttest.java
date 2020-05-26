package com.example.nagar.onlinetest;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.text.SimpleDateFormat;
import java.util.Date;

public class studenttest extends AppCompatActivity {

    ImageView imageView3;
    ImageView imageView2;
    TextView textView17;
    TextView testName;
    EditText descriptiveAns;
    Button button6;
    Button desConfirm;
    Button confirm;
    Animation rounding;
    ProgressDialog progressDialog;
    TextView question;


    RadioGroup radioGroup;
    RadioButton option1;
    RadioButton option2;
    RadioButton option3;
    RadioButton option4;


    String name;
    String  end_time1;
    String tid;
    String id1;

    List<String> qns;
    List<String> a;
    List<String> b;
    List<String> c;
    List<String> d;
    List<String> des;
    List<String> desid;
    List<String> id;


    int counter = 0,mcqcounter;
    int des1 = 0,descounter;
    int inum;
    int crtOptoin;
    int total = 0;


    String stdusn;

    @SuppressLint("SetTextI18n")
    public void start(View view)
    {

        Log.e("starttttttttttttttt","starttttttttttttttt");
        //setting mcq and descriptive counter
        descounter = desid.size();
        mcqcounter = id.size();


        //setting the timer with the given value
        Log.e("enddddddddddddddddd", String.valueOf(id.size()));
        String end_time = end_time1;
        Date date1= null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String d1;
        d1=formatter.format(date);
        Date date2= null;
        try {
            date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(d1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long duration  = date1.getTime() - date2.getTime();


        Log.e("235245324523452346","134343462346234623");
        //changing start button to confirm button
        button6.animate().alpha(0);
        button6.setEnabled(false);



        //Test name set
        testName.setText("Test : " + name);
        testName.animate().alpha(1);


        //clock and timer set
        imageView3.animate().alpha(1);
        imageView2.animate().alpha(1);
        textView17.animate().alpha(1);

        if(mcqcounter == 0)
        {
            //disabling radio grp
            radioGroup.setEnabled(false);
            option1.setEnabled(false);
            option2.setEnabled(false);
            option3.setEnabled(false);
            option4.setEnabled(false);

            //hinding radio grp
            radioGroup.animate().alpha(0);
            option1.animate().alpha(0);
            option2.animate().alpha(0);
            option3.animate().alpha(0);
            option4.animate().alpha(0);

            //disabling mcq confirm button
            confirm.setEnabled(false);
            confirm.animate().alpha(0);

            //enabling descriptive confirm button
            total++;
            desConfirm.setEnabled(true);
            desConfirm.animate().alpha(1);
            question.setText(total +" "+ des.get(des1));
            descriptiveAns.animate().alpha(1);
        }
        else {

            confirm.setEnabled(true);
            confirm.animate().alpha(1);

            desConfirm.setEnabled(false);
            desConfirm.animate().alpha(0);
            descriptiveAns.setEnabled(false);
            descriptiveAns.animate().alpha(0);

            radioGroup.animate().alpha(1);
            option1.animate().alpha(1);
            option2.animate().alpha(1);
            option3.animate().alpha(1);
            option4.animate().alpha(1);


            question.animate().alpha(1);
            total++;
            question.setText(total +" "+qns.get(counter));
            option1.setText(a.get(counter));
            option2.setText(b.get(counter));
            option3.setText(c.get(counter));
            option4.setText(d.get(counter));
        }


        imageView3.startAnimation(rounding);

        Log.e("timeeeeeeeeeeeeeeeee", String.valueOf(duration));
        Log.e("enddddddddddddd",end_time1);


        long millisinFuture = duration;
        long delayTime=1000;

        new CountDownTimer(millisinFuture, delayTime) {

            public void onTick(long millisUntilFinished) {
                int h = (int) (millisUntilFinished / 1000) / 3600;
                int m = (int) ((millisUntilFinished / 1000) % 3600) / 60;
                int s = (int) (millisUntilFinished / 1000) % 60;
                String timeLeftFormatted;
                if (h > 0) {
                    timeLeftFormatted = String.format(Locale.getDefault(),
                            "%d:%02d:%02d", h, m, s);
                } else {
                    timeLeftFormatted = String.format(Locale.getDefault(),
                            "%02d:%02d", m, s);
                }
                textView17.setText(timeLeftFormatted);
            }

            public void onFinish() {
                textView17.setText("done!");
                //button11.setEnabled(false);
                imageView3.getAnimation().cancel();
                imageView3.clearAnimation();
            }
        }.start();

        //button6.animate().alpha(0);
        //button6.setEnabled(false);
    }

    public void desConfirm(View view)
    {
        Log.e("dessssssssssssss","dessssssssssssssssss");
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        progressDialog.show();

        Log.e("crttttttttttttt", "000000000000000000000");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("crt", descriptiveAns.getText().toString());
        jsonObject.addProperty("tid", tid);
        jsonObject.addProperty("usn", stdusn);


        IRetrofitAnsSubmit jsonPostService = ServiceGenerator.createService(IRetrofitAnsSubmit.class, "http://ec2-13-233-208-238.ap-south-1.compute.amazonaws.com/");
        Call<ansSubmitObject> call = jsonPostService.postRawJSON(jsonObject);
        Log.e("call", jsonObject.toString());
        call.enqueue(new Callback<ansSubmitObject>() {

            @Override
            public void onResponse(Call<ansSubmitObject> call, Response<ansSubmitObject> response) {
                try {
                    progressDialog.dismiss();

                    Log.e("response-success", response.body().getSt());
                    if (response.body().getSt().equals("True")) {
                        if (des1 + 1 == desid.size()) {
                            Toast.makeText(studenttest.this, "Test is completed", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),student.class);
                            intent.putExtra("usn",stdusn);
                            startActivity(intent);
                        }
                        des1++;
                        total++;
                        descriptiveAns.setText("");
                        question.setText(total +" "+ des.get(des1));
                    }


                } catch (Exception e) {
                    Log.i("eeeeeeeee", "5555555555555");

                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ansSubmitObject> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(studenttest.this, "No Internet!!!", Toast.LENGTH_SHORT).show();
                Log.e("response-success", "22222222222222222222222222");
                Log.e("response-failure", call.toString());
            }

        });
    }


    public void confirm(View view)
    {

        Log.e("mcqqqqqqqqqqqqqqqqq","mcqqqqqqqqqqqqqqqqqqqqqqqqq");
        int check = Integer.parseInt(id.get(counter));
        Log.e("counter", String.valueOf(counter));
        Log.e("id", String.valueOf(check));
        check = check - 1793;

        Log.e("id", String.valueOf(check));


        if (check == 97) {
            crtOptoin = 1;
        }
        if (check == 98) {
            crtOptoin = 2;
        }
        if (check == 99) {
            crtOptoin = 3;
        }
        if (check == 100) {
            crtOptoin = 4;
        }


        if (option1.isChecked() || option2.isChecked() || option3.isChecked() || option4.isChecked()) {
            RadioButton radioSelected = findViewById(radioGroup.getCheckedRadioButtonId());
            int answer = radioGroup.indexOfChild(radioSelected) + 1;
            Log.e("crttttttttttttt", "555555555550000000000000");
            if (answer == crtOptoin) {
                Log.e("crttttttttttttt", "000000000000000000000");
                progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Uploading...");
                progressDialog.show();

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("crt", "1");
                jsonObject.addProperty("tid", tid);
                jsonObject.addProperty("usn", stdusn);


                IRetrofitAnsSubmit jsonPostService = ServiceGenerator.createService(IRetrofitAnsSubmit.class, "http://ec2-13-233-208-238.ap-south-1.compute.amazonaws.com/");
                Call<ansSubmitObject> call = jsonPostService.postRawJSON(jsonObject);
                Log.e("call", jsonObject.toString());
                call.enqueue(new Callback<ansSubmitObject>() {

                    @Override
                    public void onResponse(Call<ansSubmitObject> call, Response<ansSubmitObject> response) {
                        try {
                            progressDialog.dismiss();


                            //Log.e("response-success", response.body().getName());
                            Log.e("response-success", response.body().getSt());
                            //Log.e("response-success", "11111111111111111111111111111111111111111111111111111");
                            if (response.body().getSt().equals("True")) {
                                if (counter + 1 == id.size()) {
                                    if( desid.size() == 0)
                                    {
                                        Toast.makeText(studenttest.this, "Test is completed", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),student.class);
                                        intent.putExtra("usn",stdusn);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        confirm.setEnabled(false);
                                        confirm.animate().alpha(0);

                                        desConfirm.setEnabled(true);
                                        desConfirm.animate().alpha(1);
                                        descriptiveAns.setEnabled(true);
                                        descriptiveAns.animate().alpha(1);

                                        radioGroup.animate().alpha(0);
                                        option1.animate().alpha(0);
                                        option2.animate().alpha(0);
                                        option3.animate().alpha(0);
                                        option4.animate().alpha(0);

                                        radioGroup.setEnabled(false);
                                        option1.setEnabled(false);
                                        option2.setEnabled(false);
                                        option3.setEnabled(false);
                                        option4.setEnabled(false);

                                        total++;
                                        question.setText(total +" "+ des.get(des1));

                                    }

                                }
                                radioGroup.clearCheck();
                                counter++;
                                total++;
                                Log.e("counterr", String.valueOf(counter));
                                Log.e("ttttttttttttttttttt", "tttttttttttttttttt");
                                question.setText(total + " "+ qns.get(counter));
                                option1.setText(a.get(counter));
                                option2.setText(b.get(counter));
                                option3.setText(c.get(counter));
                                option4.setText(d.get(counter));
                            }


                        } catch (Exception e) {
                            Log.i("eeeeeeeee", "5555555555555");

                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<ansSubmitObject> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(studenttest.this, "No Internet!!!", Toast.LENGTH_SHORT).show();
                        Log.e("response-success", "22222222222222222222222222");
                        Log.e("response-failure", call.toString());
                    }

                });


            }
            else
            {
                Log.e("crttttttttttttt", "000000000000000000000");
                progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Uploading...");
                progressDialog.show();


                if (counter + 1 == id.size()) {
                    if( desid.size() == 0)
                    {
                        Toast.makeText(studenttest.this, "Test is completed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),student.class);
                        intent.putExtra("usn",stdusn);
                        startActivity(intent);
                    }
                    else
                    {
                        confirm.setEnabled(false);
                        confirm.animate().alpha(0);

                        desConfirm.setEnabled(true);
                        desConfirm.animate().alpha(1);
                        descriptiveAns.setEnabled(true);
                        descriptiveAns.animate().alpha(1);

                        radioGroup.animate().alpha(0);
                        option1.animate().alpha(0);
                        option2.animate().alpha(0);
                        option3.animate().alpha(0);
                        option4.animate().alpha(0);

                        radioGroup.setEnabled(false);
                        option1.setEnabled(false);
                        option2.setEnabled(false);
                        option3.setEnabled(false);
                        option4.setEnabled(false);


                        question.setText(total + " " +des.get(des1));

                    }

                }

                progressDialog.dismiss();

                radioGroup.clearCheck();
                counter++;
                Log.e("counterr", String.valueOf(counter));
                Log.e("ttttttttttttttttttt", "tttttttttttttttttt");
                total++;
                question.setText(total + " " + qns.get(counter));
                option1.setText(a.get(counter));
                option2.setText(b.get(counter));
                option3.setText(c.get(counter));
                option4.setText(d.get(counter));


            }

        } else {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studenttest);

        imageView3 = (ImageView)findViewById(R.id.imageView3);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        textView17 = (TextView)findViewById(R.id.textView17);
        button6 = (Button)findViewById(R.id.button6);
        confirm = (Button)findViewById(R.id.button7);
        desConfirm = (Button)findViewById(R.id.button8);
        testName = (TextView)findViewById(R.id.textView9);
        descriptiveAns = (EditText)findViewById(R.id.editText7);

        rounding = AnimationUtils.loadAnimation(this,R.anim.rounding);


        question = (TextView)findViewById(R.id.textView7);
        radioGroup = (RadioGroup)findViewById(R.id.radiogrp);
        option1 = (RadioButton)findViewById(R.id.option1);
        option2 = (RadioButton)findViewById(R.id.option2);
        option3 = (RadioButton)findViewById(R.id.option3);
        option4 = (RadioButton)findViewById(R.id.option4);


        confirm.setEnabled(false);


        Intent intent = getIntent();
        stdusn = intent.getStringExtra("usn");



        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");
        progressDialog.show();

        Log.e("aaaaaaaaaaaaaa",stdusn);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("stdusn",stdusn);

        IRetrofitStudentTest jsonPostService = ServiceGenerator.createService(IRetrofitStudentTest.class, "http://ec2-13-233-208-238.ap-south-1.compute.amazonaws.com/");
        Call<List<studentTestObject>> call = jsonPostService.postRawJSON(jsonObject);
        Log.e("call",jsonObject.toString());
        call.enqueue(new Callback<List<studentTestObject>>() {

            @Override
            public void onResponse(Call<List<studentTestObject>> call, Response<List<studentTestObject>> response) {
                try{
                    progressDialog.dismiss();
                    Log.e("response-success", "11111111111111111111111111111111111111111111111111111");

                    List<studentTestObject> list = response.body();

                    id = new ArrayList<>();
                    a = new ArrayList<>();
                    b = new ArrayList<>();
                    c = new ArrayList<>();
                    d = new ArrayList<>();
                    qns = new ArrayList<>();
                    des = new ArrayList<>();
                    desid = new ArrayList<>();


                    for(studentTestObject i : list )
                    {

                        id1 = i.getId();
                        Log.e("wwwwwwwwwwwwwww",i.getId());


                        if(id1.equals("1") )
                        {
                            name =  i.getTest_name();
                            end_time1 = i.getEnd_time();
                            tid = i.getTid();
                            Log.e("wwwwwwwwwwwwwww",name);
                            Log.e("wwwwwwwwwwwwwww",end_time1);
                            continue;
                        }
                        if(id1.equals("000") )
                        {
                            Toast.makeText(studenttest.this, "Currently no test available", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),student.class);
                            intent.putExtra("usn",stdusn);
                            startActivity(intent);
                            //return;
                        }
                        if ((inum = Integer.parseInt(i.getId()))<1850)
                        {
                            des.add(i.getQns());
                            desid.add(i.getId());
                            //Log.e("dessssssssssss",des.get(0));
                        }
                        else
                        {
                            id.add(i.getId());
                            a.add(i.getA());
                            b.add(i.getB());
                            c.add(i.getC());
                            d.add(i.getD());
                            qns.add(i.getQns());
                            Log.e("qns",i.getQns());
                        }


                    }


                }catch (Exception e){
                    Log.i("eeeeeeeee","5555555555555");

                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<List<studentTestObject>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(studenttest.this, "No Internet!!!", Toast.LENGTH_SHORT).show();
                Log.e("response-success", "22222222222222222222222222");
                Log.e("response-failure", call.toString());
            }

        });

    }
}
