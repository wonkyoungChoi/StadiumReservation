package com.example.stadiumreservation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

public class MyMatchInfo2 extends AppCompatActivity {
    TextView tName, stName, start, finish, ability, number;
    Button cancel, back;
    String name, stname, startDate, startTime, finishDate, finishTime, abil, num, myId, otherid;
    String myid = LoginActivity.id;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), MyMatch.class);
        startActivity(intent);
        finish();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mymatch_info);

        cancel = (Button) findViewById(R.id.match);
        back = (Button) findViewById(R.id.applyCancel);
        tName = (TextView) findViewById(R.id.team);
        stName = (TextView) findViewById(R.id.stadium);
        start = (TextView) findViewById(R.id.startDateTime);
        finish = (TextView) findViewById(R.id.finishDateTime);
        ability = (TextView) findViewById(R.id.ability_);
        number = (TextView) findViewById(R.id.number_);

        Intent intent = getIntent();
        ReservationValue reservationValue = (ReservationValue) intent.getSerializableExtra("ReservationValue");
        name = reservationValue.getTeamName();
        stname = reservationValue.getStadiumName();
        startDate = reservationValue.getStartDate();
        startTime = reservationValue.getStartTime();
        finishDate = reservationValue.getFinishDate();
        finishTime = reservationValue.getFinishTime();
        abil = reservationValue.getAbility();
        num = reservationValue.getNumber();


        tName.setText(name);
        stName.setText(stname);
        start.setText(startDate + " " + startTime);
        finish.setText(finishDate + " " + finishTime);
        ability.setText(abil);
        number.setText(num);


        //예약취소 버튼
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

                CustomTask task = new CustomTask();
                String result;
                try {
                    result = task.execute(stname, startDate, startTime, finishTime).get();
                    Log.d("delete", result);
                    myId = substringBetween(result, ":", "*");
                    Log.d("myId", myId);
                    otherid = substringBetween(result, "*", "^");
                    Log.d("otherid", otherid);
                    CustomTask2 task1 = new CustomTask2();
                    if(myid.equals(myId)) {
                        task1.execute(otherid, stname, startDate, startTime, finishTime).get();
                        Intent intent = new Intent(getApplicationContext(), MyMatch.class);
                        startActivity(intent);
                        finish();
                    } else if(myid.equals(otherid)) {
                        task1.execute(myId, stname, startDate, startTime, finishTime).get();
                        Intent intent = new Intent(getApplicationContext(), MyMatch.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "예약 취소", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        //뒤로가기 버튼
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://192.168.0.15:8080/adaptedcancel.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "&stadium="+strings[0] + "&startdate="+strings[1]
                        + "&starttime="+strings[2] + "&finishtime="+strings[3];
                osw.write(sendMsg);
                osw.flush();
                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();

                } else {
                    Log.i("통신 결과", conn.getResponseCode()+"에러");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return receiveMsg;
        }
    }

    class CustomTask2 extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://192.168.0.15:8080/adaptedcancel1.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0] +"&stadium="+strings[1] + "&startdate="+strings[2]
                        + "&starttime="+strings[3] + "&finishtime="+strings[4];
                osw.write(sendMsg);
                osw.flush();
                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();

                } else {
                    Log.i("통신 결과", conn.getResponseCode()+"에러");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return receiveMsg;
        }
    }

    private String substringBetween(String str, String open, String close) {
        if (str == null || open == null || close == null) {
            return null;
        }
        int start = str.indexOf(open);
        if (start != -1) {
            int end = str.indexOf(close, start + open.length());
            if (end != -1) {
                return str.substring(start + open.length(), end);
            }
        }
        return null;
    }



}
