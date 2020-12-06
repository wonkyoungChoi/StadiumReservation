package com.example.stadiumreservation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Apply extends AppCompatActivity {

    static int check_code = -1;
    static int init_code = 1;
    static int clicked_item = -1;

    String name;
    String stname;
    String startDate;
    String startTime;
    String finishDate;
    String finishTime;
    String abil;
    String num;

    ArrayList<ReservationValue> items = ApplyAdapter.items;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply);

        if(check_code==1) {
            Intent intent = getIntent();
            name = intent.getExtras().getString("name", "");
            stname = intent.getExtras().getString("stname", "");
            startDate = intent.getExtras().getString("startDate", "");
            startTime = intent.getExtras().getString("startTime", "");
            finishDate = intent.getExtras().getString("finishDate", "");
            finishTime = intent.getExtras().getString("finishTime", "");
            abil = intent.getExtras().getString("abil", "");
            num = intent.getExtras().getString("num", "");
        }

        RecyclerView applyRecyclerview = findViewById(R.id.applyRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        applyRecyclerview.setLayoutManager(layoutManager);

        ApplyAdapter adapter = new ApplyAdapter(items);
        if(init_code==1) {
            adapter.addItem(new ReservationValue("FC서울", "서울월드컵경기장", "2020.12.15", "12:00",
                    " 2020.12.15", "14:00", "중", "5:5"));
            adapter.addItem(new ReservationValue("대구FC", "DGB 대구은행파크", "2020.12.16", "13:00",
                    " 2020.12.16", "15:00", "상", "6:6"));
            adapter.addItem(new ReservationValue("전북 현대", "전주월드컵경기장", "2020.12.20", "20:00",
                    " 2020.12.20", "22:00", "중상", "5:5"));
            adapter.addItem(new ReservationValue("울산 현대", "울산문수월드컵경기장", "2020.12.15", "12:00",
                    " 2020.12.15", "14:00", "하", "6:6"));
            adapter.addItem(new ReservationValue("광주FC", "광주축구전용구장", "2020.12.15", "14:00",
                    " 2020.12.18", "19:00", "중하", "5:5"));
            adapter.addItem(new ReservationValue("성남FC", "탄천종합운동장", "2020.12.15", "14:00",
                    " 2020.12.25", "22:00", "중상", "5:5"));
            adapter.addItem(new ReservationValue("인천유나이티드", "인천축구전용경기장", "2020.12.15", "14:00",
                    " 2020.12.24", "14:00", "상", "6:6"));
            adapter.addItem(new ReservationValue("부산아이파크", "부산 구덕운동장", "2020.12.15", "14:00",
                    " 2020.12.30", "18:00", "중하", "무관"));
            adapter.addItem(new ReservationValue("포항스틸러스", "상주시민운동장", "2020.12.15", "14:00",
                    " 2020.12.27", "20:00", "하", "5:5"));
            init_code = -1;
        }

        if(check_code==1) {
            adapter.removeItem(clicked_item);
        }

        adapter.setOnItemClickListener(new ApplyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                clicked_item = pos;
                Intent intent = new Intent(getApplicationContext(), ApplyInfo.class);
                intent.putExtra("applyValue", adapter.getItem(pos));

                startActivity(intent);
            }
        });

        applyRecyclerview.setAdapter(adapter);
    }

    public void onClicked(View v) {
        Intent in = null;

        switch(v.getId()) {
            case R.id.goMenu_btn:
                in = new Intent(getApplicationContext(), MainMenu.class);
                break;
            case R.id.myMatch_btn :
                in = new Intent(getApplicationContext(), MyMatch.class);
                break;
        }
        startActivity(in);
        finish();
    }
}