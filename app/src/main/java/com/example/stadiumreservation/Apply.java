package com.example.stadiumreservation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Apply extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply);

        RecyclerView applyRecyclerview = findViewById(R.id.applyRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        applyRecyclerview.setLayoutManager(layoutManager);

        ApplyAdapter adapter = new ApplyAdapter();
        adapter.addItem(new ReservationValue("FC서울", "서울월드컵경기장", "2020.12.15", "12:00",
                " 2020.12.15", "14:00", "중", "5:5"));
        adapter.addItem(new ReservationValue("대구FC", "DGB 대구은행파크", "2020.12.16", "13:00",
                " 2020.12.16", "15:00", "상", "6:6"));
        adapter.addItem(new ReservationValue("전북 현대", "전주월드컵경기장", "2020.12.20", "20:00",
                " 2020.12.20", "22:00", "중상", "5:5"));
        adapter.addItem(new ReservationValue("울산 현대", "울산문수월드컵경기장", "2020.12.15", "12:00",
                " 2020.12.15", "14:00", "하", "6:6"));
        adapter.addItem(new ReservationValue("상주상무", "상주시민운동장", "2020.12.15", "14:00",
                " 2020.12.15", "16:00", "중하", "5:5"));

        adapter.setOnItemClickListener(new ApplyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Intent intent = new Intent(getApplicationContext(), ApplyInfo.class);
                intent.putExtra("applyValue", adapter.getItem(pos));

                startActivity(intent);
            }
        });

        applyRecyclerview.setAdapter(adapter);
    }
}