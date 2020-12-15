package com.example.stadiumreservation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MyMatchInfo extends AppCompatActivity {
    TextView tName, stName, start, finish, ability, number;
    Button cancel, back;
    String name, stname, startDate, startTime, finishDate, finishTime, abil, num;

    int clicked_item = MyMatch.clicked_item;
    ReservationAdapter adapter = new ReservationAdapter(ReservationAdapter.items);

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
                //클릭한 아이템 삭제
                adapter.removeItem(clicked_item);

                Intent intent = new Intent(getApplicationContext(), MyMatch.class);
                startActivity(intent);
            }
        });


        //뒤로가기 버튼
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), MyMatch.class);
                startActivity(intent);
            }
        });

    }

}
