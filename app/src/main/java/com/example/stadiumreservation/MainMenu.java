package com.example.stadiumreservation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class MainMenu extends AppCompatActivity {
    String nick = LoginActivity.nick;
    TextView userHello, logout;
    int i = 0;

    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        //메뉴 상단 오른쪽 인사
        userHello = (TextView) findViewById(R.id.userHello);
        userHello.setText(nick + "님 안녕하세요!");

        logout = (TextView) findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), LoginActivity.class);
                in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(i>=1) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "뒤로가기를 한번 더 누르면 로그아웃됩니다.", Toast.LENGTH_SHORT).show();
            i++;
        }
    }

    public void onClicked(View v) {
        Intent in = null;

        switch(v.getId()) {
            case R.id.reservation :
                in = new Intent(getApplicationContext(), Reservation.class);
                break;
            case R.id.apply:
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