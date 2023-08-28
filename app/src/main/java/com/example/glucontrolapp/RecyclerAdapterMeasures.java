package com.example.glucontrolapp;

import android.content.Context;
import android.os.Bundle;
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
import com.example.glucontrolapp.DatabaseUtils.MeasuresDao;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterMeasures extends RecyclerView.Adapter<RecyclerAdapterMeasures.MeasuresHolder> {

    MeasuresDao measuresDao;

    private Context context;
    private List<Measures> listMeasures;
    private static final String TAG = "RecyclerAdapterMeasure";

    private RecyclerViewMeasures activity;

    private AdapterListener adapterListener;

    EditText editMeasures;

    private UpdateMeasure update;


    public RecyclerAdapterMeasures(Context context, AdapterListener adapterListener){
        this.context =context;
        listMeasures = new ArrayList<>();
        this.adapterListener=adapterListener;

    }

   public void removeMeasures(int position){
        listMeasures.remove(position);
        notifyDataSetChanged();
   }

    @NonNull
    @Override
    public MeasuresHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_recycler_measures, parent , false);
        return new MeasuresHolder(itemView) ;



    }

    @Override
    public void onBindViewHolder(@NonNull MeasuresHolder holder, int position) {

        Measures  currentMeasure = listMeasures.get(position);
        String measureValue = String.valueOf(currentMeasure.getMeasure());
        String dateValue= currentMeasure.getDate().toString();
        Log.d(TAG ,"fecha: :" + dateValue);

        holder.measures.setText(measureValue);
        holder.date.setText(dateValue);



        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                adapterListener.onDelete(currentMeasure.getId(), holder.getAdapterPosition());
                notifyDataSetChanged();

            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterListener.onUpdate(currentMeasure);
                notifyDataSetChanged();
                //double newValue= update.updateMeasures(v);
                //holder.measures.setText(String.valueOf(newValue));

            }
        });

    }

    @Override
    public int getItemCount() {
        return listMeasures.size();
    }


    public void setMeasures(List<Measures> measures){
        this.listMeasures = measures;
        notifyDataSetChanged();
    }


    public void deleteMeasure(int position){


        Measures item =listMeasures.get(position);
        measuresDao.deleteMeasures( item.getId());
    }

    public void editMeasure(int position){
        Measures item = listMeasures.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putDouble("measure", item.getMeasure());
    }

    public Context getContext(){
        return activity;
    }

    public static class MeasuresHolder extends RecyclerView.ViewHolder{

        private TextView measures;
        private TextView date;

        private ImageView edit, delete;

        public MeasuresHolder(@NonNull View itemView) {
            super(itemView);
            measures = itemView.findViewById(R.id.measureId);
            date = itemView.findViewById(R.id.quantity_card);

            edit=itemView.findViewById(R.id.editMeasure);
            delete=itemView.findViewById(R.id.deleteMeasure);




        }


    }


}
