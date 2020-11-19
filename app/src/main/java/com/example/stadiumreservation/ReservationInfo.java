package com.example.stadiumreservation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ReservationInfo extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_result);

        ReservationAdapter adapter = new ReservationAdapter();
        Button regist = (Button) findViewById(R.id.regist);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Reservation.class);
                startActivity(intent);
            }
        });

            Intent intent = getIntent();
            ReservationValue reservationValue =
                    (ReservationValue) intent.getSerializableExtra("reservationValue");

            String name = reservationValue.getTeamName();
            String stName = reservationValue.getStadiumName();
            String startDate = reservationValue.getStartDate();
            String startTime = reservationValue.getStartTime();
            String finishDate = reservationValue.getFinishDate();
            String finishTime = reservationValue.getFinishTime();
            String ability = reservationValue.getAbility();
            String number = reservationValue.getNumber();

            adapter.addItem(0, new ReservationValue(name, stName, startDate, startTime,
                    finishDate, finishTime, ability, number));

        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        }
    }
