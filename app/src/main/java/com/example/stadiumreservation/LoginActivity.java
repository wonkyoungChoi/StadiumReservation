package com.example.stadiumreservation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        EditText username = (EditText) findViewById(R.id.username);

        EditText password = (EditText) findViewById(R.id.password);
        Button login = (Button) findViewById(R.id.loginbtn);
        Button cancel = (Button) findViewById(R.id.cancelbtn);

        //취소 버튼 클릭
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //로그인 버튼 클릭
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = username.getText().toString();
                String passwd = password.getText().toString();
                if(name.trim().length()>0
                && passwd.trim().length()>0) {
                    Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                    intent.putExtra("name", name);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "다시 입력하시오.",
                        Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
