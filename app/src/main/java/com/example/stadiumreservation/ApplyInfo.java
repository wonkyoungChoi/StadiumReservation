package com.example.stadiumreservation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ApplyInfo extends AppCompatActivity {
    TextView tName, stName, start, finish, ability, number;
    Button match, cancel;

    int clicked_item = Apply.clicked_item;
    ArrayList<ReservationValue> list = new ArrayList<>();
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

        //신청하기 클릭
        match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //내 매치정보의 리스트에 추가
                list.add(reservationValue);

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

}
