package com.example.stadiumreservation;

import android.content.Intent;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link my_reservation#newInstance} factory method to
 * create an instance of this fragment.
 */
public class my_reservation extends Fragment {

    static int clicked_item;
    ReservationValue reservationValue;
    String nick = LoginActivity.nick;

    ArrayList<ReservationValue> list = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public my_reservation() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment my_reservation.
     */
    // TODO: Rename and change types and number of parameters
    public static my_reservation newInstance(String param1, String param2) {
        my_reservation fragment = new my_reservation();
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
        Log.d("nick", nick);
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
                Intent intent = new Intent(getContext(), MyMatchInfo.class);
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
                list.remove(viewHolder.getLayoutPosition());
                adapter.notifyItemRemoved(viewHolder.getLayoutPosition());
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        // Inflate the layout for this fragment
        return v;
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

                if(nickname.equals(nick) && !id.contains("/")) {
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