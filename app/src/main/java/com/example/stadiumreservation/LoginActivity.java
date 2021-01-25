package com.example.stadiumreservation;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button login, cancel;
    TextView regist, findId;
    static String nick;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = (EditText) findViewById(R.id.username);

        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.loginbtn);
        cancel = (Button) findViewById(R.id.cancelbtn);
        regist = (TextView) findViewById(R.id.regist);
        findId = (TextView) findViewById(R.id.findId);

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
                    try {
                        String result;
                        CustomTask task = new CustomTask();
                        result = task.execute(name, passwd).get();
                        Log.i("리턴 값",result);
                        if(result.contains("true")) {
                            username.setText("");
                            password.setText("");
                            nick = substringBetween(result, ":", "/");
                            Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                            startActivity(intent);
                        } else{
                            Toast.makeText(getApplicationContext(), "아이디 혹은 비밀번호를 다시 입력하시오.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "아이디 혹은 비밀번호를 입력하세요.",
                        Toast.LENGTH_SHORT).show();
                }
            }
        });

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Regist.class);
                startActivity(intent);
            }
        });

    }

    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://192.168.0.15:8080/login.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0]+"&pwd="+strings[1];
                osw.write(sendMsg);
                osw.flush();
                if(conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8);
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();

                } else {
                    Log.i("통신 결과", conn.getResponseCode()+"에러");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return receiveMsg;
        }
    }

    private String substringBetween(String str, String open, String close) {
        if (str == null || open == null || close == null) {
            return null;
        }
        int start = str.indexOf(open);
        if (start != -1) {
            int end = str.indexOf(close, start + open.length());
            if (end != -1) {
                return str.substring(start + open.length(), end);
            }
        }
        return null;
    }

}
