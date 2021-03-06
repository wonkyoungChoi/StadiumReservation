package com.example.stadiumreservation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link my_info#newInstance} factory method to
 * create an instance of this fragment.
 */
public class my_info extends Fragment {
    String id = LoginActivity.id;
    TextView nickname, textId;
    ImageButton nickname_edit;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public my_info() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment my_info.
     */
    // TODO: Rename and change types and number of parameters
    public static my_info newInstance(String param1, String param2) {
        my_info fragment = new my_info();
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
        View v = inflater.inflate(R.layout.fragment_my_info, container, false);
        nickname = (TextView) v.findViewById(R.id.nickname);
        nickname_edit = (ImageButton) v.findViewById(R.id.nickname_edit);
        textId = (TextView) v.findViewById(R.id.id);
        String result;
        String nick, myid;
        CustomTask task = new CustomTask();
        try {
            result = task.execute(id).get();
            nick = substringBetween(result, "nick:", "/");
            nickname.setText(nick);
            myid = substringBetween(result, "id:", "/");
            textId.setText(myid);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        nickname_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(v.getContext());

                AlertDialog.Builder dlg = new AlertDialog.Builder(v.getContext());
                dlg.setTitle("닉네임 변경");
                dlg.setCancelable(false);
                dlg.setView(editText);
                dlg.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                dlg.setPositiveButton("수정하기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String result, nick;
                        nick = editText.getText().toString();
                        CustomTask2 task = new CustomTask2();
                        try {
                            result = task.execute(nick, id).get();
                            Log.d("nickUpdate", result);
                            if(result.contains("sameNick")) {
                                Toast.makeText(getContext(), "중복된 닉네임입니다.",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), "닉네임을 변경하였습니다.",
                                        Toast.LENGTH_SHORT).show();
                                nickname.setText(nick);
                            }
                        } catch (ExecutionException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                dlg.show();
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

    class CustomTask extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://192.168.0.15:8080/profile.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "id="+strings[0];
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

    class CustomTask2 extends AsyncTask<String, Void, String> {
        String sendMsg, receiveMsg;
        @Override
        protected String doInBackground(String... strings) {
            try {
                String str;
                URL url = new URL("http://192.168.0.15:8080/profilechange.jsp");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
                sendMsg = "nick="+strings[0] + "&id="+strings[1];
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