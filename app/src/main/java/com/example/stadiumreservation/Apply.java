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

    static int init_code = 1;
    static int clicked_item = -1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply);

        RecyclerView applyRecyclerview = findViewById(R.id.applyRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        applyRecyclerview.setLayoutManager(layoutManager);

        ApplyAdapter adapter = new ApplyAdapter(ApplyAdapter.items);
        //전역변수 설정을 통해 Apply에 다시 와도 Item은 한번만 추가
        if (init_code == 1) {
            adapter.addItem(new ReservationValue("FC서울", "서울월드컵경기장", "2020.12.15", "12시 00분",
                    " 2020.12.15", "14시 00분", "중", "5:5"));
            adapter.addItem(new ReservationValue("대구FC", "DGB 대구은행파크", "2020.12.16", "13시 30분",
                    " 2020.12.16", "15시 30분", "상", "6:6"));
            adapter.addItem(new ReservationValue("전북 현대", "전주월드컵경기장", "2020.12.20", "20시 00분",
                    " 2020.12.20", "22시 00분", "중상", "5:5"));
            adapter.addItem(new ReservationValue("울산 현대", "울산문수월드컵경기장", "2020.12.15", "12:00",
                    " 2020.12.15", "14시 00분", "하", "6:6"));
            adapter.addItem(new ReservationValue("광주FC", "광주축구전용구장", "2020.12.15", "14시 00분",
                    " 2020.12.18", "16시 00분", "중하", "5:5"));
            adapter.addItem(new ReservationValue("성남FC", "탄천종합운동장", "2020.12.15", "14시 20분",
                    " 2020.12.25", "16시 20분", "중상", "5:5"));
            adapter.addItem(new ReservationValue("인천유나이티드", "인천축구전용경기장", "2020.12.15", "14시 00분",
                    " 2020.12.24", "16시 00분", "상", "6:6"));
            adapter.addItem(new ReservationValue("부산아이파크", "부산 구덕운동장", "2020.12.15", "15시 30분",
                    " 2020.12.30", "17시 30분", "중하", "무관"));
            adapter.addItem(new ReservationValue("포항스틸러스", "상주시민운동장", "2020.12.15", "12시 40분",
                    " 2020.12.27", "14시 40분", "하", "5:5"));
            init_code = -1;
        }

        //리사이클러 뷰 item 클릭
        adapter.setOnItemClickListener(new ApplyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //클릭한 item의 position을 전역변수로 설정해 Info로 가져감
                clicked_item = pos;
                finish();
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
    }
}