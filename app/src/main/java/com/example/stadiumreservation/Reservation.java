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



    String abilitySelected , numberSelected ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation);

        EditText teamName = (EditText) findViewById(R.id.writeName);
        EditText stadiumName = (EditText) findViewById(R.id.writeStadium);

        RadioGroup abilityrg = (RadioGroup) findViewById(R.id.abilityGroup);
        RadioGroup numberrg = (RadioGroup) findViewById(R.id.numberGroup);
        RadioButton rd1 = (RadioButton) findViewById(abilityrg.getCheckedRadioButtonId());
        RadioButton rd2 = (RadioButton) findViewById(numberrg.getCheckedRadioButtonId());

        Button btnSelectDate = (Button) findViewById(R.id.btnSelectDate);
        Button btnSelectTime = (Button) findViewById(R.id.btnSelectTime);
        Button btnFinishDate = (Button) findViewById(R.id.btnFinishDate);
        Button btnFinishTime = (Button) findViewById(R.id.btnFinishTime);

        String SelectDate = btnSelectDate.getText().toString();
        String SelectTime = btnSelectTime.getText().toString();
        String FinishDate = btnFinishDate.getText().toString();
        String FinishTime = btnFinishTime.getText().toString();

        final String Tname = teamName.getText().toString();
        final String Stname = stadiumName.getText().toString();
/*


 */

        Button ok = (Button) findViewById(R.id.ok);
        Button cancel = (Button) findViewById(R.id.cancel);

        abilityrg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch(checkedId) {
                    case R.id.ability1:
                        Toast.makeText(getApplicationContext(), abilitySelected + " 선택됨",
                                Toast.LENGTH_SHORT).show();
                    case R.id.ability2:
                        Toast.makeText(getApplicationContext(), abilitySelected + " 선택됨",
                                Toast.LENGTH_SHORT).show();
                    case R.id.ability3:
                        Toast.makeText(getApplicationContext(), abilitySelected + " 선택됨",
                                Toast.LENGTH_SHORT).show();
                    case R.id.ability4:
                        Toast.makeText(getApplicationContext(), abilitySelected + " 선택됨",
                                Toast.LENGTH_SHORT).show();
                    case R.id.ability5:
                        Toast.makeText(getApplicationContext(), abilitySelected + " 선택됨",
                                Toast.LENGTH_SHORT).show();
                    case R.id.ability6:
                        Toast.makeText(getApplicationContext(), abilitySelected + " 선택됨",
                                Toast.LENGTH_SHORT).show();
                }
            }
        });


        numberrg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch(checkedId) {
                    case R.id.number1:
                        Toast.makeText(getApplicationContext(), numberSelected + " 선택됨",
                                Toast.LENGTH_SHORT).show();
                    case R.id.number2:
                        Toast.makeText(getApplicationContext(), numberSelected + " 선택됨",
                                Toast.LENGTH_SHORT).show();
                    case R.id.number3:
                        Toast.makeText(getApplicationContext(), numberSelected + " 선택됨",
                                Toast.LENGTH_SHORT).show();
                }

            }
        });

        abilitySelected = rd1.getText().toString();
        numberSelected = rd2.getText().toString();


        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance(); //현재 시간을 얻음
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getApplicationContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        btnSelectDate.setText(year + "년 " + monthOfYear + "월 " + dayOfMonth + "일");
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = 0;

                TimePickerDialog timePickerDialog = new TimePickerDialog(getApplicationContext(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        btnSelectTime.setText(hourOfDay + "시" + minute + "분");
                    }
                }, mHour, mMinute, true);
                timePickerDialog.show();
            }
        });

        btnFinishDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance(); //현재 시간을 얻음
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getApplicationContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        btnFinishDate.setText(year + "년 " + monthOfYear + "월 " + dayOfMonth + "일");
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        btnFinishTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = 0;

                TimePickerDialog timePickerDialog = new TimePickerDialog(getApplicationContext(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        btnFinishTime.setText(hourOfDay + "시" + minute + "분");
                    }
                }, mHour, mMinute, true);
                timePickerDialog.show();
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Tname.trim().length() > 0
                        && Stname.trim().length() > 0) {
                    Intent intent = new Intent(getApplicationContext(), ReservationInfor.class);
                    intent.putExtra("teamName", Tname);
                    intent.putExtra("stadiumName", Stname);
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
}