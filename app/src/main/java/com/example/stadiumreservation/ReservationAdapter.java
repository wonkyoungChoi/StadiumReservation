package com.example.stadiumreservation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder>{

    ArrayList<ReservationValue> items = new ArrayList<>();

    public void addItem(ReservationValue item) {
        items.add(item);
    }
    public ReservationValue getItem(int position) {return items.get(position);}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.reservation_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReservationValue item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() { return items.size(); }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tName;
        TextView staName;
        TextView StrDate;
        TextView StrTime;
        TextView FinDate;
        TextView FinTime;
        TextView abil;
        TextView num;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tName = itemView.findViewById(R.id.teamName1);
            staName = itemView.findViewById(R.id.stadiumName1);
            StrDate = itemView.findViewById(R.id.startDate1);
            StrTime = itemView.findViewById(R.id.startTime1);
            FinDate = itemView.findViewById(R.id.finishDate1);
            FinTime = itemView.findViewById(R.id.finishTime1);
            abil = itemView.findViewById(R.id.ability1);
            num = itemView.findViewById(R.id.number);

        }

        public void setItem(ReservationValue item) {
            tName.setText(item.getTeamName());
            staName.setText(item.getStadiumName());
            StrDate.setText(item.getStartDate());
            StrTime.setText(item.getStartTime());
            FinDate.setText(item.getFinishDate());
            FinTime.setText(item.getFinishTime());
            abil.setText(item.getAbility());
            num.setText(item.getNumber());

        }
    }
}
