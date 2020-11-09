package com.example.stadiumreservation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Intent intent = getIntent();
        String id = intent.getStringExtra("name");

        TextView userHello = (TextView) findViewById(R.id.userHello);
        userHello.setText(id + "님 안녕하세요!");

    }

    public void onClicked(View v) {
        Intent in = null;

        switch(v.getId()) {
            case R.id.reservation :
                in = new Intent(getApplicationContext(), Reservation.class);
                break;
            case R.id.apply :
                in = new Intent(getApplicationContext(), Apply.class);
                break;
            case R.id.search :
                in = new Intent(getApplicationContext(), Search.class);
                break;
            case R.id.mymatch :
                in = new Intent(getApplicationContext(), MyMatch.class);
        }
        startActivity(in);
    }


}