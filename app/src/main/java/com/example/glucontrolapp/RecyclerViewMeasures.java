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
import android.view.View;
import android.widget.ImageView;

import com.example.glucontrolapp.DatabaseUtils.AdapterListener;
import com.example.glucontrolapp.DatabaseUtils.AppDatabase;
import com.example.glucontrolapp.DatabaseUtils.Measures;
import com.example.glucontrolapp.DatabaseUtils.MeasuresDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerViewMeasures extends AppCompatActivity implements AdapterListener {

    private List<Measures> mList;
    RecyclerView recyclerView;

    RecyclerAdapterMeasures measuresAdapter;

    private ImageView edit, delete;

    MeasuresDao measuresDao;

    String email;

    private static final int REQUEST_CODE_UPDATE_MEASURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_measures);

        SharedPreferences sharedPreferences = getSharedPreferences("sharing_email", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("email", "email is not available");

        AppDatabase db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbTest"
        ).allowMainThreadQueries().build();

        measuresDao = db.measuresDao();

        recyclerView = findViewById(R.id.recycler_view_measures);
        mList= new ArrayList<>();
        measuresAdapter = new RecyclerAdapterMeasures(this, this);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(measuresAdapter);

        mList = measuresDao.getMeasures(email);
        if (mList.isEmpty()) {
            finish();

            Intent intent = new Intent(RecyclerViewMeasures.this, EmptyRecords.class);
            startActivity(intent);

        } else {
            Collections.reverse(mList);
            measuresAdapter.setMeasures(mList);
        }


    }



    @Override
    public void onUpdate(Measures measures) {
        //measuresDao.updateMeasure(id);
        String TAG = "asses";

        Intent intent = new Intent(this, UpdateMeasure.class);
        intent.putExtra("new", measures);
        startActivityForResult(intent, REQUEST_CODE_UPDATE_MEASURE);

        measuresAdapter.notifyDataSetChanged();

    }

    @Override
    public void onDelete(int id, int pos) {
        measuresDao.deleteMeasures(id);
        measuresAdapter.removeMeasures(pos);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String TAG = "asses on fire";
        if (requestCode == REQUEST_CODE_UPDATE_MEASURE && resultCode == RESULT_OK) {
            AppDatabase db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbTest"
            ).allowMainThreadQueries().build();

            measuresDao = db.measuresDao();
            //RecyclerAdapterMeasures adapter = new RecyclerAdapterMeasures(db);
            recyclerView = findViewById(R.id.recycler_view_measures);
            mList= new ArrayList<>();
            measuresAdapter = new RecyclerAdapterMeasures(this, this);

            //measuresAdapter = new RecyclerAdapterMeasures(this));

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(measuresAdapter);

            mList = measuresDao.getMeasures(email);
            Collections.reverse(mList);
            measuresAdapter.setMeasures(mList);
        }
    }
}
