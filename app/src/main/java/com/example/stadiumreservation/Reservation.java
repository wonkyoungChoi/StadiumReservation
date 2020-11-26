package com.example.stadiumreservation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;

public class Reservation extends AppCompatActivity {
    RadioGroup abilityrg;
    RadioGroup numberrg;
    static int check_click = 0;
    int ability_click = 0;
    int number_click = 0;


    String abilitySelected , numberSelected ;
    Button btnSelectDate, btnSelectTime, btnFinishDate, btnFinishTime, ok, cancel;
    String tname, stname, SelectDate, SelectTime, FinishDate, FinishTime;
    EditText teamName, stadiumName;

    ReservationValue reservationValue;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);


        teamName = (EditText) findViewById(R.id.writeName);
        stadiumName = (EditText) findViewById(R.id.writeStadium);

        abilityrg = (RadioGroup) findViewById(R.id.abilityGroup);
        numberrg = (RadioGroup) findViewById(R.id.numberGroup);

        btnSelectDate = (Button) findViewById(R.id.btnSelectDate);
        btnSelectTime = (Button) findViewById(R.id.btnSelectTime);
        btnFinishDate = (Button) findViewById(R.id.btnFinishDate);
        btnFinishTime = (Button) findViewById(R.id.btnFinishTime);

        reservationValue = new ReservationValue(tname, stname, SelectDate,
                SelectTime, FinishDate, FinishTime, abilitySelected, numberSelected);



        ok = (Button) findViewById(R.id.ok);
        cancel = (Button) findViewById(R.id.cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tname = teamName.getText().toString();
                stname = stadiumName.getText().toString();
                SelectDate = btnSelectDate.getText().toString();
                SelectTime = btnSelectTime.getText().toString();
                FinishDate = btnFinishDate.getText().toString();
                FinishTime = btnFinishTime.getText().toString();

                reservationValue.setTeamName(tname);
                reservationValue.setStadiumName(stname);
                reservationValue.setStartDate(SelectDate);
                reservationValue.setStartTime(SelectTime);
                reservationValue.setFinishDate(FinishDate);
                reservationValue.setFinishTime(FinishTime);
                reservationValue.setAbility(abilitySelected);
                reservationValue.setNumber(numberSelected);

                if (ability_click == 1 && number_click == 1 && tname.trim().length() > 0 && stname.trim().length() > 0
                        && SelectDate.trim().length() > 4 && SelectTime.trim().length() > 4 && FinishDate.trim().length() > 4
                        && FinishTime.trim().length() > 4 && abilitySelected.trim().length() > 0 && numberSelected.trim().length() > 0) {
                    check_click = 1;

                    Intent intent = new Intent(getApplicationContext(), ReservationInfo.class);
                    Intent intent1 = new Intent(getApplicationContext(), MyMatch.class);
                    intent.putExtra("reservationValue", reservationValue);
                    intent1.putExtra("reservationValue", reservationValue);

                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "모든 정보를 입력하시오.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void abilityButtonClicked(View v){
        ability_click=1;
        switch(v.getId()) {
            case R.id.ability1:
                abilitySelected = "하";
                Toast.makeText(getApplicationContext(), abilitySelected + " 선택됨",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.ability2:
                abilitySelected ="중하";
                Toast.makeText(getApplicationContext(), abilitySelected + " 선택됨",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.ability3:
                abilitySelected ="중";
                Toast.makeText(getApplicationContext(), abilitySelected + " 선택됨",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.ability4:
                abilitySelected ="중상";
                Toast.makeText(getApplicationContext(), abilitySelected + " 선택됨",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.ability5:
                abilitySelected ="상";
                Toast.makeText(getApplicationContext(), abilitySelected + " 선택됨",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.ability6:
                abilitySelected ="무관";
                Toast.makeText(getApplicationContext(), abilitySelected + " 선택됨",
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void numberButtonClicked(View v){
        number_click=1;
        switch(v.getId()) {
            case R.id.number1:
                numberSelected="5:5";
                Toast.makeText(getApplicationContext(), numberSelected + " 선택됨",
                        Toast.LENGTH_SHORT).show();
                break;

            case R.id.number2:
                numberSelected="6:6";
                Toast.makeText(getApplicationContext(), numberSelected + " 선택됨",
                        Toast.LENGTH_SHORT).show();
                break;

            case R.id.number3:
                numberSelected="무관";
                Toast.makeText(getApplicationContext(), numberSelected + " 선택됨",
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void onClicked (View v){
        if (v == btnSelectDate) {
            final Calendar c = Calendar.getInstance(); //현재 시간을 얻음
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    btnSelectDate.setText(year + "년 " + monthOfYear + "월 " + dayOfMonth + "일");
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnSelectTime) {
            final Calendar c = Calendar.getInstance();
            int mHour = c.get(Calendar.HOUR_OF_DAY);
            int mMinute = 0;

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    btnSelectTime.setText(hourOfDay + "시 " + minute + "분");
                }
            }, mHour, mMinute, true);
            timePickerDialog.show();
        }

        if (v == btnFinishDate) {
            final Calendar c = Calendar.getInstance(); //현재 시간을 얻음
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    btnFinishDate.setText(year + "년 " + monthOfYear + "월 " + dayOfMonth + "일");
                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if (v == btnFinishTime) {
            final Calendar c = Calendar.getInstance();
            int mHour = c.get(Calendar.HOUR_OF_DAY);
            int mMinute = 0;

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    btnFinishTime.setText(hourOfDay + "시 " + minute + "분");
                }
            }, mHour, mMinute, true);
            timePickerDialog.show();
        }
    }



}