package com.example.stadiumreservation;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

    public class MyMatch extends AppCompatActivity {
        BottomNavigationView bottomNavigationView;
        my_info fragment1;
        my_reservation fragment2;
        adapted_reservation fragment3;
        @Override protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.mymatch);
            bottomNavigationView = findViewById(R.id.bottom_navigation_view);

            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //프래그먼트 생성
            fragment1 = new my_info();
            fragment2 = new my_reservation();
            fragment3 = new adapted_reservation();

            //제일 처음 띄워줄 뷰를 세팅해줍니다. commit();까지 해줘야 합니다.
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout ,fragment1).commitAllowingStateLoss();

            //bottomnavigationview의 아이콘을 선택 했을때 원하는 프래그먼트가 띄워질 수 있도록 리스너를 추가합니다.
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    //menu_bottom.xml에서 지정해줬던 아이디 값을 받아와서 각 아이디값마다 다른 이벤트를 발생시킵니다.
                    case R.id.navigation_menu1:{
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout,fragment1).commitAllowingStateLoss();
                        return true; }
                    case R.id.navigation_menu2:{
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout,fragment2).commitAllowingStateLoss();
                        return true; }
                    case R.id.navigation_menu3:{
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frame_layout,fragment3).commitAllowingStateLoss();
                        return true; }
                    case R.id.navigation_menu4:{
                        finish();
                    }
                    default: return false;
                }
            }
            });
        }

}
