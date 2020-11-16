package com.example.stadiumreservation;

import java.io.Serializable;

public class ReservationValue implements Serializable {
    String teamName;
    String stadiumName;
    String startDate;
    String startTime;
    String finishDate;
    String finishTime;
    String ability;
    String number;


    public ReservationValue(String teamName, String stadiumName, String startDate, String startTime,
                            String finishDate, String finishTime, String ability, String number) {
        this.teamName = teamName;
        this.stadiumName = stadiumName;
        this.startDate = startDate;
        this.startTime = startTime;
        this.finishDate = finishDate;
        this.finishTime = finishTime;
        this.ability = ability;
        this.number = number;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
