package com.example.stadiumreservation;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ApplyAdapter extends RecyclerView.Adapter<ApplyAdapter.ViewHolder> {

    ApplyAdapter(ArrayList<ReservationValue> list) {items = list;}

    public interface OnItemClickListener{
        void onItemClick(View v, int pos);
    }
    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {this.mListener = listener;}

    static ArrayList<ReservationValue> items = new ArrayList<>();

    public void addItem(ReservationValue item) {items.add(item);}

    public void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, items.size());
    }

    public ReservationValue getItem(int position) {return items.get(position);}

    @NonNull
    @Override
    public ApplyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.apply_item, parent, false);
        return new ApplyAdapter.ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ApplyAdapter.ViewHolder holder, int position) {
        ReservationValue item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() { return items.size(); }

    class ViewHolder extends RecyclerView.ViewHolder{
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

            tName = itemView.findViewById(R.id.teamName);
            staName = itemView.findViewById(R.id.stadiumName);
            StrDate = itemView.findViewById(R.id.startDate);
            StrTime = itemView.findViewById(R.id.startTime);
            FinDate = itemView.findViewById(R.id.finishDate);
            FinTime = itemView.findViewById(R.id.finishTime);
            abil = itemView.findViewById(R.id.ability);
            num = itemView.findViewById(R.id.number);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION) {
                        if(mListener != null) {
                            mListener.onItemClick(v, pos);
                        }
                    }
                }
            });
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
