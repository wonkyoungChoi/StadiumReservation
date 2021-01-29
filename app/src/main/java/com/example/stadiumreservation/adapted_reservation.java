package com.example.stadiumreservation;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link adapted_reservation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class adapted_reservation extends Fragment {

    static int clicked_item;
    ReservationValue reservationValue;
    String nick = LoginActivity.nick;
    String myid = LoginActivity.id;

    ArrayList<ReservationValue> list = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public adapted_reservation() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment adapted_reservation.
     */
    // TODO: Rename and change types and number of parameters
    public static adapted_reservation newInstance(String param1, String param2) {
        adapted_reservation fragment = new adapted_reservation();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_reservation, container, false);

        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        list.clear();
        try {
            Log.d("json",downloadUrl());
            jsonParsing(downloadUrl());
            Log.d("reservation", String.valueOf(reservationValue));

        } catch (IOException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = v.findViewById(R.id.infoRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        ReservationAdapter adapter = new ReservationAdapter(list);
        recyclerView.setAdapter(adapter);

        //리사이클러뷰 아이템 클릭
        adapter.setOnItemClickListener(new ReservationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                clicked_item = pos;
                Intent intent = new Intent(getContext(), MyMatchInfo2.class);
                intent.putExtra("ReservationValue", adapter.getItem(pos));

                startActivity(intent);
            }
        });


        //스와이프하여 항목 삭제 기능
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                String stname, startDate, startTime, finishTime;
                ReservationValue value;
                value = list.get(viewHolder.getLayoutPosition());
                stname = value.getStadiumName();
                startDate = value.getStartDate();
                startTime = value.getStartTime();
                finishTime = value.getFinishTime();
                CustomTask task = new CustomTask();
                try {
                    task.execute(stname, startDate, startTime, finishTime).get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getContext(), "예약 취소", Toast.LENGTH_SHORT).show();
                list.remove(viewHolder.getLayoutPosition());
                adapter.notifyItemRemoved(viewHolder.getLayoutPosition());

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        // Inflate the layout for this fragment
        return v;
    }

    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://192.168.0.15:8080/adaptedcancel.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "stadium="+strings[0] + "&startdate="+strings[1]
                        + "&starttime="+strings[2] + "&finishtime="+strings[3];
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

                if(id.contains(myid) && id.contains("/")) {
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