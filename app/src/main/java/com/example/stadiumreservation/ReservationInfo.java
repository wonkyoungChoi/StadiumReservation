package com.example.stadiumreservation;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
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

public class ReservationInfo extends AppCompatActivity {

    ArrayList<ReservationValue> list = new ArrayList<>();
    ReservationValue reservationValue;
    String nick = LoginActivity.nick;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_result);
        list.clear();

        Log.d("nick", nick);
        try {
            Log.d("json",downloadUrl());
            jsonParsing(downloadUrl());
            Log.d("reservation", String.valueOf(reservationValue));

        } catch (IOException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        ReservationAdapter adapter = new ReservationAdapter(list);
        recyclerView.setAdapter(adapter);

    }

    public void onClicked(View v) {
        Intent in = null;

        switch(v.getId()) {
            case R.id.regist :
                in = new Intent(getApplicationContext(), Reservation.class);
                finish();
                break;
            case R.id.goMenu_btn:
                in = new Intent(getApplicationContext(), MainMenu.class);
                finish();
                break;
            case R.id.myMatch_btn :
                in = new Intent(getApplicationContext(), MyMatch.class);
                finish();
                break;
        }
        startActivity(in);
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
        String nickname;
        String id;
        try{
            JSONArray reservationArray = new JSONArray(json);

            for(int i=0; i<reservationArray.length(); i++)
            {
                JSONObject reservationObject = reservationArray.getJSONObject(i);

                nickname = reservationObject.getString("nick");
                id = reservationObject.getString("id");
                reservationValue = new ReservationValue();
                Log.d("nick3", nickname);

                if(nickname.equals(nick) && !id.contains("*")) {
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
