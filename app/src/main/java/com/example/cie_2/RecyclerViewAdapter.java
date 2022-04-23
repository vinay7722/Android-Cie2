package com.example.cie_2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    ArrayList<FuelItemEntry> list;

    public RecyclerViewAdapter(ArrayList<FuelItemEntry> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.driver_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.date.setText(list.get(position).getDate());
        holder.volume.setText(list.get(position).getVolume());
        holder.price.setText(list.get(position).getRupeesPerLite());
        holder.previousOdometerReading.setText(list.get(position).getPreviousOdometerReading());
        holder.currentOdometerReading.setText(list.get(position).getCurrentOdometerReading());
        holder.average.setText(list.get(position).getAverage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView date, volume, price, previousOdometerReading, currentOdometerReading, average;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.idate);
            volume = itemView.findViewById(R.id.ivolume);
            price = itemView.findViewById(R.id.ipricePerLitre);
            currentOdometerReading = itemView.findViewById(R.id.icurrentOdometer);
            previousOdometerReading = itemView.findViewById(R.id.ipreviousOdometer);
            average = itemView.findViewById(R.id.iaverage);
        }
    }
}
