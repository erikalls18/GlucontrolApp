package com.example.glucontrolapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.glucontrolapp.DatabaseUtils.AdapterListener;
import com.example.glucontrolapp.DatabaseUtils.AppDatabase;
import com.example.glucontrolapp.DatabaseUtils.Measures;
import com.example.glucontrolapp.DatabaseUtils.MedicationsDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerViewMedications extends AppCompatActivity implements AdapterListener {

    String email;

    MedicationsDao medicationsDao;
    RecyclerView recyclerView;

    RecyclerAdapterMedications medicationAdapter;

    List mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_medications);
        SharedPreferences sharedPreferences = getSharedPreferences("sharing_email", Context.MODE_PRIVATE);

       email = sharedPreferences.getString("email", "email is not available");

        AppDatabase db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbTest"
        ).allowMainThreadQueries().build();

        medicationsDao = db.medicationsDao();

        recyclerView = findViewById(R.id.recycler_view_medication);
        mList= new ArrayList<>();
        medicationAdapter = new RecyclerAdapterMedications(this, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(medicationAdapter);

        mList = medicationsDao.getMedications(email);
        if (mList.isEmpty()) {
            finish();

            Intent intent = new Intent(RecyclerViewMedications.this, EmptyMedication.class);
            startActivity(intent);

        } else {
            Collections.reverse(mList);
            medicationAdapter.setMedications(mList);
        }

    }
    @Override
    public void onUpdate(Measures measures) {

    }

    @Override
    public void onDelete(int id, int pos) {
        medicationsDao.deleteMedication(id);
        medicationAdapter.removeMedications(pos);
    }
}