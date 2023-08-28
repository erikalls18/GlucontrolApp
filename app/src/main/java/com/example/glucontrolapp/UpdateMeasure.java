package com.example.glucontrolapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Measure;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.glucontrolapp.DatabaseUtils.AppDatabase;
import com.example.glucontrolapp.DatabaseUtils.Measures;
import com.example.glucontrolapp.DatabaseUtils.MeasuresDao;
import com.example.glucontrolapp.DatabaseUtils.Users;

public class UpdateMeasure extends AppCompatActivity {

    private Measures values;

    private EditText newMeasure;

    private AppDatabase appDatabase;

    private MeasuresDao measuresDao;

    private static final String TAG = "updatemeasure";

    Button btnUpdate;

    String current_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_measure);
        newMeasure =findViewById(R.id.newMeasure);

        values= (Measures)getIntent().getSerializableExtra("new");
        newMeasure.setText(String.valueOf(values.getMeasure()));

        btnUpdate =findViewById(R.id.update);


        SharedPreferences sharedPreferences = getSharedPreferences("sharing_email", Context.MODE_PRIVATE);
        current_login = sharedPreferences.getString("email", "email is not available");


        Intent intent = getIntent();
        if (intent != null) {
            // Recibir el objeto extra
            Object values = intent.getSerializableExtra("new");
        } btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppDatabase db = Room.databaseBuilder(
                        getApplicationContext(),
                        AppDatabase.class,
                        "dbTest"
                ).allowMainThreadQueries().build();



                double updated_measure = Double.parseDouble(newMeasure.getText().toString());


                Measures newMeasure1 = new Measures(values.getMeasure(), current_login, values.getId());
                measuresDao = db.measuresDao();
                measuresDao.updateMeasure( current_login,updated_measure, newMeasure1.getId());

                newMeasure.setText(String.valueOf(updated_measure));
                newMeasure.invalidate();

                setResult(RESULT_OK);
                finish();

            }
        });
    }


}