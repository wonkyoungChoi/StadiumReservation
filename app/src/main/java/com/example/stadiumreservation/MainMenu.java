package com.example.stadiumreservation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class MainMenu extends AppCompatActivity {
    static String nick;
    TextView userHello;

    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Intent intent = getIntent();
        nick = intent.getStringExtra("nick");

        //메뉴 상단 오른쪽 인사
        userHello = (TextView) findViewById(R.id.userHello);
        userHello.setText(nick + "님 안녕하세요!");

    }

    public void onClicked(View v) {
        Intent in = null;

        switch(v.getId()) {
            case R.id.reservation :
                in = new Intent(getApplicationContext(), Reservation.class);
                in.putExtra("nick", nick);
                break;
            case R.id.apply:
                in = new Intent(getApplicationContext(), Apply.class);
                break;
            case R.id.search :
                in = new Intent(getApplicationContext(), Search.class);
                break;
            case R.id.mymatch :
                in = new Intent(getApplicationContext(), MyMatch.class);
                break;
        }
        startActivity(in);
    }


}