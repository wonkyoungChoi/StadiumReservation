package com.example.stadiumreservation;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Apply extends AppCompatActivity {

    static int clicked_item = -1;
    static ArrayList<ReservationValue> list = new ArrayList<>();
    ReservationValue reservationValue;
    String nick = LoginActivity.nick;
    Button goMenu, myMatch;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apply);

        goMenu = (Button) findViewById(R.id.goMenu_btn);
        goMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        myMatch = (Button) findViewById(R.id.myMatch_btn);
        myMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyMatch.class);
                startActivity(intent);
                finish();
            }
        });

        list.clear();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        try {
            Log.d("json",downloadUrl());
            jsonParsing(downloadUrl());
        } catch (IOException e) {
            e.printStackTrace();
        }

        RecyclerView applyRecyclerview = findViewById(R.id.applyRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        applyRecyclerview.setLayoutManager(layoutManager);

        ApplyAdapter adapter = new ApplyAdapter(list);



        //리사이클러 뷰 item 클릭
        adapter.setOnItemClickListener(new ApplyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                //클릭한 item의 position을 전역변수로 설정해 Info로 가져감
                clicked_item = pos;
                Log.d("value", reservationValue.getTeamName());
                finish();
                Intent intent = new Intent(getApplicationContext(), ApplyInfo.class);
                intent.putExtra("applyValue", reservationValue);

                startActivity(intent);
            }
        });

        applyRecyclerview.setAdapter(adapter);
    }


    private String downloadUrl() throws IOException {
        String s = null;
        byte[] buffer = new byte[10000];
        InputStream iStream = null;
        try {
            URL url = new URL("http://192.168.0.15:8080/reservationjson.jsp");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.connect();

            iStream = urlConnection.getInputStream();
            iStream.read(buffer);
            s = new String(buffer);
        } catch (Exception e) {
            Log.d("Exception download", e.toString());
        } finally {
            if(iStream != null) {
                iStream.close();
            } else
                Log.d("iStreamNull", "iStreamNull");
        }
        return s;
    }



    //Json Parsing
    private void jsonParsing(String json)
    {
        String nickname, id;
        try{
            JSONArray reservationArray = new JSONArray(json);

            for(int i=0; i<reservationArray.length(); i++)
            {
                JSONObject reservationObject = reservationArray.getJSONObject(i);

                nickname = reservationObject.getString("nick");
                id = reservationObject.getString("id");
                reservationValue = new ReservationValue();
                Log.d("ID", id);
                if(!id.contains("/") && !nickname.equals(nick)) {
                    reservationValue.setTeamName(reservationObject.getString("teamname"));
                    reservationValue.setStadiumName(reservationObject.getString("stadium"));
                    reservationValue.setStartDate(reservationObject.getString("startdate"));
                    reservationValue.setStartTime(reservationObject.getString("starttime"));
                    reservationValue.setFinishDate(reservationObject.getString("finishdate"));
                    reservationValue.setFinishTime(reservationObject.getString("finishtime"));
                    reservationValue.setAbility(reservationObject.getString("ability"));
                    reservationValue.setNumber(reservationObject.getString("number"));
                    list.add(reservationValue);
                }
            }
        }catch (JSONException e) {

            e.printStackTrace();
        }
    }


}