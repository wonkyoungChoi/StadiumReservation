package com.example.stadiumreservation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Search extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);

        Button goMenu = (Button) findViewById(R.id.goMenu);
        ImageButton goNext = (ImageButton) findViewById(R.id.nextButton);

        goMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        /*public void Initialize() {
            reservationValue = new ReservationValue();
            reservationValue.setTeamName(teamname);
            reservationValue.setStadiumName(stadiumname);
            reservationValue.setStartDate(SelectDate);
            reservationValue.setStartTime(SelectTime);
            reservationValue.setFinDate(FinishDate);
            reservationValue.setFinTime(FinishTime);
            reservationValue.setAbility(abilitySelected);
            reservationValue.setNumber(numberSelected);
        }
*/

    }
}