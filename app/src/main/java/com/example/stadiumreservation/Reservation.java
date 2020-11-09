package com.example.stadiumreservation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.Calendar;

public class Reservation extends AppCompatActivity {
    private RadioGroup abilityrg, numberrg;

    EditText teamName = (EditText) findViewById(R.id.writeName);
    EditText stadiumName = (EditText) findViewById(R.id.writeStadium);

    String abilitySelected, numberSelected;

    Button btnSelectDate = (Button) findViewById(R.id.btnSelectDate);
    Button btnSelectTime = (Button) findViewById(R.id.btnSelectTime);
    Button btnFinishDate = (Button) findViewById(R.id.btnFinishDate);
    Button btnFinishTime = (Button) findViewById(R.id.btnFinishTime);



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);

        Button ok = (Button) findViewById(R.id.ok);
        Button cancel = (Button) findViewById(R.id.cancel);


        abilityrg = (RadioGroup) findViewById(R.id.abilityGroup);
        numberrg = (RadioGroup) findViewById(R.id.numberGroup);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (teamName.getText().toString().trim().length() > 0
                        && stadiumName.getText().toString().trim().length() > 0) {

                    String teamname = teamName.getText().toString();
                    String stadiumname = stadiumName.getText().toString();
                    String SelectDate = btnSelectDate.getText().toString();
                    String SelectTime = btnSelectTime.getText().toString();
                    String FinishDate = btnFinishDate.getText().toString();
                    String FinishTime = btnFinishTime.getText().toString();

                    Intent intent = new Intent(getApplicationContext(), ReservationInfor.class);
                    intent.putExtra("teamName", teamname);
                    intent.putExtra("stadiumName", stadiumname);
                    intent.putExtra("startDate", SelectDate);
                    intent.putExtra("startTime", SelectTime);
                    intent.putExtra("finishDate", FinishDate);
                    intent.putExtra("finishTime", FinishTime);
                    intent.putExtra("ability", abilitySelected);
                    intent.putExtra("number", numberSelected);

                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "다시 입력하시오.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

        public void abilityButtonClicked (View v){
            RadioButton rd = (RadioButton) findViewById(abilityrg.getCheckedRadioButtonId());
            abilitySelected = rd.getText().toString();

            Toast.makeText(getApplicationContext(), abilitySelected + " 선택됨",
                    Toast.LENGTH_SHORT).show();
        }

        public void numberButtonClicked (View v){
            RadioButton rd = (RadioButton) findViewById(numberrg.getCheckedRadioButtonId());
            numberSelected = rd.getText().toString();

            Toast.makeText(getApplicationContext(), numberSelected + " 선택됨",
                    Toast.LENGTH_SHORT).show();
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
                        btnSelectTime.setText(hourOfDay + "시" + minute + "분");
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
                        btnFinishTime.setText(hourOfDay + "시" + minute + "분");
                    }
                }, mHour, mMinute, true);
                timePickerDialog.show();
            }
        }
}