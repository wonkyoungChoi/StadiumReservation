package com.example.stadiumreservation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ApplyInfo extends AppCompatActivity {
    TextView tName, stName, start, finish, ability, number;
    Button match, cancel;

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


        match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Apply.check_code=1;
                Intent intent = new Intent(getApplicationContext(), Apply.class);
                intent.putExtra("name", name);
                intent.putExtra("stname", stname);
                intent.putExtra("startDate", startDate);
                intent.putExtra("startTime", startTime);
                intent.putExtra("finishDate", finishDate);
                intent.putExtra("finishTime", finishTime);
                intent.putExtra("abil", abil);
                intent.putExtra("num", num);
                startActivity(intent);
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });

    }

}
