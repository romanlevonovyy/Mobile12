package com.vitalik.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.EquipmentViewHolder> {


    private List<Equipment> equipmentList;
    private Context context;

    public EquipmentAdapter(Context context, List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
        this.context = context;
    }

    @NonNull
    @Override
    public EquipmentAdapter.EquipmentViewHolder onCreateViewHolder(@NonNull final ViewGroup parent,
                                                                   final int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_equipment, parent, false);
        return new EquipmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final EquipmentViewHolder holder,
                                 final int position) {
//        holder.temperaturel.setText(equipmentList.get(position).getTemperaturel());
        holder.madename.setText(equipmentList.get(position).getMadename());
//        holder.atmotusk.setText(equipmentList.get(position).getAtmotusk());
//        holder.dataservice.setText(equipmentList.get(position).getDataservice());
//        holder.location.setText(equipmentList.get(position).getLocation());
//        holder.napryamok.setText(equipmentList.get(position).getNapryamok());
//        holder.datastart.setText(equipmentList.get(position).getDatastart());
//        holder.speedviter.setText(equipmentList.get(position).getSpeedviter());
//        holder.vologist.setText(equipmentList.get(position).getVologist());
        holder.description.setText(equipmentList.get(position).getDescription());
        holder.parentLayout.setOnClickListener(view -> openItemDetails(position));
    }

    @Override
    public int getItemCount() {
        return equipmentList.size();
    }

    class EquipmentViewHolder extends RecyclerView.ViewHolder {

//        private TextView temperaturel;
        private TextView madename;
//        private TextView atmotusk;
//        private TextView location;
        private TextView description;
//        private TextView dataservice;
//        private TextView napryamok;
//        private TextView datastart;
//        private TextView speedviter;
//        private TextView vologist;

        private LinearLayout parentLayout;

        private EquipmentViewHolder(final View itemView) {
            super(itemView);

//            temperaturel = itemView.findViewById(R.id.item_temperaturel);
            madename = itemView.findViewById(R.id.item_madename);
//            atmotusk = itemView.findViewById(R.id.item_atmotusk);
//            location = itemView.findViewById(R.id.item_location);
//            dataservice = itemView.findViewById(R.id.item_dataservice);
//            napryamok = itemView.findViewById(R.id.item_napryamok);
//            datastart = itemView.findViewById(R.id.item_datastart);
//            speedviter = itemView.findViewById(R.id.item_speedviter);
//            vologist = itemView.findViewById(R.id.item_vologist);
            description = itemView.findViewById(R.id.item_description);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

    private void openItemDetails(int position) {
        Intent intent = new Intent(context, EquipmentDetailsActivity.class);
        intent.putExtra("temperaturel", equipmentList.get(position).getTemperaturel());
        intent.putExtra("madename", equipmentList.get(position).getMadename());
        intent.putExtra("atmotusk", equipmentList.get(position).getAtmotusk());
        intent.putExtra("location", equipmentList.get(position).getLocation());
        intent.putExtra("dataservice", equipmentList.get(position).getDataservice());
        intent.putExtra("napryamok", equipmentList.get(position).getNapryamok());
        intent.putExtra("datastart", equipmentList.get(position).getDatastart());
        intent.putExtra("speedviter", equipmentList.get(position).getSpeedviter());
        intent.putExtra("vologist", equipmentList.get(position).getVologist());
        intent.putExtra("description", equipmentList.get(position).getDescription());


        context.startActivity(intent);
    }
}