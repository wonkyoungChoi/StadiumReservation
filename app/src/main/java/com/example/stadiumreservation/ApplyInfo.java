package com.example.stadiumreservation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ApplyInfo extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply_info);

        TextView tName = (TextView) findViewById(R.id.team);
        TextView stName = (TextView) findViewById(R.id.stadium);
        TextView start = (TextView) findViewById(R.id.startDateTime);
        TextView finish = (TextView) findViewById(R.id.finishDateTime);
        TextView ability = (TextView) findViewById(R.id.ability_);
        TextView number = (TextView) findViewById(R.id.number_);

        Intent intent = getIntent();
        ReservationValue reservationValue = (ReservationValue) intent.getSerializableExtra("applyValue");

        String name = reservationValue.getTeamName();
        String stname = reservationValue.getStadiumName();
        String startDate = reservationValue.getStartDate();
        String startTime = reservationValue.getStartTime();
        String finishDate = reservationValue.getFinishDate();
        String finishTime = reservationValue.getFinishTime();
        String abil = reservationValue.getAbility();
        String num = reservationValue.getNumber();

        tName.setText(name);
        stName.setText(stname);
        start.setText(startDate + " " + startTime);
        finish.setText(finishDate + " " + finishTime);
        ability.setText(abil);
        number.setText(num);


    }
}
