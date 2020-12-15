package com.example.stadiumreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClicked(View v) {
        Intent intent = null;

        switch (v.getId()) {
            case R.id.login:
                intent = new Intent(getApplicationContext(), LoginActivity.class);
                break;
            case R.id.information:
                intent = new Intent(getApplicationContext(), Information.class);
        }
        startActivity(intent);
    }



}