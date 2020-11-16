package com.example.stadiumreservation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ReservationInfor extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_result);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        ReservationAdapter adapter = new ReservationAdapter();


        Intent intent = getIntent();
        ReservationValue reservationValue =
                (ReservationValue)intent.getSerializableExtra("reservationValue");

        String name = reservationValue.getTeamName();
        String stName = reservationValue.getStadiumName();
        String startDate = reservationValue.getStartDate();
        String startTime = reservationValue.getStartTime();
        String finishDate = reservationValue.getFinishDate();
        String finishTime = reservationValue.getFinishTime();
        String ability = reservationValue.getAbility();
        String number = reservationValue.getNumber();

        adapter.addItem(new ReservationValue(name, stName, startDate, startTime,
                finishDate, finishTime, ability, number));

        recyclerView.setAdapter(adapter);
    }


}