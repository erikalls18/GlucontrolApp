package com.example.glucontrolapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.glucontrolapp.DatabaseUtils.AppDatabase;
import com.example.glucontrolapp.DatabaseUtils.MeasuresDao;

public class DisplayResultMeasures extends AppCompatActivity {

    TextView min_level, avg_level, max_level;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result_measures);

        min_level = findViewById(R.id.min_level);
        max_level = findViewById(R.id.max_level);
        avg_level = findViewById(R.id.avg_level);

        SharedPreferences sharedPreferences = getSharedPreferences("sharing_email", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "email is not available");

        AppDatabase db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbTest"
        ).allowMainThreadQueries().build();

        MeasuresDao measuresDao = db.measuresDao();
        double minimum = measuresDao.getMinimumMeasure(email);
        double maxim = measuresDao.getMaximMeasure(email);
        double average = measuresDao.getAverageMeasure(email);
        String formattedAverage = String.format("%.1f", average);

        min_level.setText(String.valueOf(minimum));
        max_level.setText(String.valueOf(maxim));
        avg_level.setText(String.valueOf(formattedAverage));


    }
}