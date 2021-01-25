package com.example.stadiumreservation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ApplyInfo extends AppCompatActivity {
    TextView tName, stName, start, finish, ability, number;
    String name, stname, startDate, startTime, finishDate, finishTime, abil, num;
    Button match, cancel;
    String id = LoginActivity.id;

    int clicked_item = Apply.clicked_item;
    ApplyAdapter adapter = new ApplyAdapter(ApplyAdapter.items);

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_info);


        match = (Button) findViewById(R.id.match);
        cancel = (Button) findViewById(R.id.applyCancel);
        tName = (TextView) findViewById(R.id.team);
        stName = (TextView) findViewById(R.id.stadium);
        start = (TextView) findViewById(R.id.startDateTime);
        finish = (TextView) findViewById(R.id.finishDateTime);
        ability = (TextView) findViewById(R.id.ability_);
        number = (TextView) findViewById(R.id.number_);

        Intent intent = getIntent();
        ReservationValue reservationValue = (ReservationValue) intent.getSerializableExtra("applyValue");

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

        //신청하기 클릭
        match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result;
                CustomTask task = new CustomTask();
                String s1 = tName.getText().toString();
                String s2 = stName.getText().toString();

                try {
                    Log.d("value", id+name+stname+startDate+finishDate);
                    result = task.execute(id, s1, s2, startDate, finishDate).get();
                    Log.d("result", result);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }

                //Apply에서 가져온 position값을 통해 삭제
                adapter.removeItem(clicked_item);
                finish();
                Intent intent = new Intent(getApplicationContext(), Apply.class);
                startActivity(intent);
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), Apply.class);
                startActivity(intent);
            }
        });

    }

    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://192.168.0.15:8080/adaptedcheck.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0] + "&teamname="+strings[1] + "&stadium="+strings[2] +
                        "&startdate="+strings[3] + "&finishdate="+strings[4];
                Log.d("SEND", sendMsg);
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
}

