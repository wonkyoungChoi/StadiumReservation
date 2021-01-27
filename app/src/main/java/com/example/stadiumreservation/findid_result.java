package com.example.stadiumreservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class findid_result extends AppCompatActivity {
    TextView result;
    Button login, findpwd;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_id_result);
        result = (TextView) findViewById(R.id.result);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        result.setText(id);

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                finish();
                startActivity(intent);
            }
        });

        findpwd = (Button) findViewById(R.id.findpwd);
        findpwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), findPwd.class);
                finish();
                startActivity(intent);
            }
        });

    }


}