package com.example.lab1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder> {

    private List<Hospital> hospitalList;

    public HospitalAdapter(List<Hospital> hospitalList) {
        this.hospitalList = hospitalList;
    }

    @NonNull
    @Override
    public HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from (parent.getContext ())
                .inflate (R.layout.list_hospital, parent, false);
        return new HospitalViewHolder (itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalViewHolder holder, int position){
        holder.fullNamePatient.setText(hospitalList.get(position).getFullNamePatient());
        holder.hospitalBuilding.setText(hospitalList.get(position).getHospitalBuilding());
        holder.ward.setText(hospitalList.get(position).getWard());
        holder.pressure.setText(hospitalList.get(position).getPressure());
        holder.temperature.setText(hospitalList.get(position).getTemperature());
        holder.palpitation.setText(hospitalList.get(position).getPalpitation());
        Picasso.get().load(hospitalList.get(position).getPhotoUrl()).into(holder.photoUrl);
    }

    @Override
    public int getItemCount() {
        return hospitalList.size ();
    }

    public class HospitalViewHolder extends RecyclerView.ViewHolder {
        private TextView hospitalBuilding;
        private TextView ward;
        private TextView fullNamePatient;
        private TextView pressure;
        private TextView temperature;
        private TextView palpitation;
        private ImageView photoUrl;

        private HospitalViewHolder(View view) {
            super (view);
            photoUrl = view.findViewById (R.id.image);
            hospitalBuilding = view.findViewById (R.id.hospital_building);
            ward = view.findViewById (R.id.ward);
            fullNamePatient = view.findViewById (R.id.full_name_patient);
            pressure = view.findViewById (R.id.pressure);
            temperature = view.findViewById (R.id.temperature);
            palpitation = view.findViewById (R.id.palpitation);
        }
    }
}