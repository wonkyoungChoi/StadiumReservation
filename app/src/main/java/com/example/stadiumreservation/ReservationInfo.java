package com.example.stadiumreservation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReservationInfo extends AppCompatActivity {

    static ArrayList<ReservationValue> list = new ArrayList<>();
    ArrayList<ReservationValue> list2 = MyMatch.list;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_result);

        //입력한 값 받아오기
        Intent intent = getIntent();
        ReservationValue reservationValue =
                (ReservationValue) intent.getSerializableExtra("reservationValue");
        
        //list에 추가
        list.add(reservationValue);
        list2.add(reservationValue);
        
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        ReservationAdapter adapter = new ReservationAdapter(list);
        recyclerView.setAdapter(adapter);

    }

    public void onClicked(View v) {
        Intent in = null;

        switch(v.getId()) {
            case R.id.regist :
                in = new Intent(getApplicationContext(), Reservation.class);
                break;
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
