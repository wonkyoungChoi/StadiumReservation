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
    int check_click = Reservation.check_click;
    Button regist;

    static ArrayList<ReservationValue> list = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_result);

        if (check_click == 1) {
            Intent intent = getIntent();
            ReservationValue reservationValue =
                    (ReservationValue) intent.getSerializableExtra("reservationValue");
            list.add(reservationValue);
        }


        regist = (Button) findViewById(R.id.regist);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Reservation.class);
                startActivity(intent);
                finish();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        ReservationAdapter adapter = new ReservationAdapter(list);
        recyclerView.setAdapter(adapter);


    }
}
