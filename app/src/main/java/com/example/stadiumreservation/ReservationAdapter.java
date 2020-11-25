package com.example.stadiumreservation;

import android.app.AlertDialog;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder>{

    static ArrayList<ReservationValue> items = null;

    //생성자에서 데이터 리스트 객체를 전달받음
    ReservationAdapter(ArrayList<ReservationValue> list) {items = list;}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.reservation_item, parent, false);

        return new ViewHolder(itemView);
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ReservationValue item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() { return items.size(); }


    //아이템 뷰를 저장하는 뷰홀더 클래스
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
