package com.example.glucontrolapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.glucontrolapp.DatabaseUtils.AdapterListener;
import com.example.glucontrolapp.DatabaseUtils.Measures;
import com.example.glucontrolapp.DatabaseUtils.Medications;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterMedications  extends RecyclerView.Adapter<RecyclerAdapterMedications.MedicationsHolder> {

    private AdapterListener adapterListener;
    private Context context;
    List<Medications> listMedications ;

    RecyclerAdapterMedications(){
        this.listMedications  =new ArrayList<>();
    }

    public RecyclerAdapterMedications(Context context, AdapterListener adapterListener){
        this.context =context;
        listMedications = new ArrayList<>();
        this.adapterListener=adapterListener;

    }

    @NonNull
    @Override
    public MedicationsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_recycler_medication, parent , false);
        return new  MedicationsHolder(itemView) ;

    }

    @Override
    public void onBindViewHolder(@NonNull MedicationsHolder holder, int position) {

        Medications currentMedication= listMedications.get(position);
        String nameMed = currentMedication.getNameMedication();
        String timesPerDay = String.valueOf(currentMedication.getTimesPerDay());
        String quantity = String.valueOf(currentMedication.getQuantity());
        String start= currentMedication.getStartDate().toString();


        holder.name.setText(nameMed);
        holder.times.setText(timesPerDay);
        holder.quantity.setText(quantity);
        holder.date.setText(start);

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterListener.onDelete(currentMedication.getId(), holder.getAdapterPosition());
                notifyDataSetChanged();

            }
        });

    }

    @Override
    public int getItemCount() {
        return listMedications.size();
    }

    public void setMedications(List<Medications> medications){
        this.listMedications = medications;
        notifyDataSetChanged();
    }

    public void removeMedications(int position){
        listMedications.remove(position);
        notifyDataSetChanged();
    }
    public static class MedicationsHolder extends RecyclerView.ViewHolder {

        TextView name, times, quantity, date;
        private ImageView delete;

        public MedicationsHolder(@NonNull View itemView) {
            super(itemView);



            name =itemView.findViewById(R.id.medication_card);
            times=itemView.findViewById(R.id.times_card);
            quantity=itemView.findViewById(R.id.quantity_card);
            date =itemView.findViewById(R.id.date_card);
            delete=itemView.findViewById(R.id.deleteMedication);
        }
    }

}
