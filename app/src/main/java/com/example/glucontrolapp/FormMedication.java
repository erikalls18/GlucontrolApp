package com.example.glucontrolapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.glucontrolapp.DatabaseUtils.AppDatabase;
import com.example.glucontrolapp.DatabaseUtils.MeasuresDao;
import com.example.glucontrolapp.DatabaseUtils.Medications;
import com.example.glucontrolapp.DatabaseUtils.MedicationsDao;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class FormMedication extends AppCompatActivity {


    EditText nameMedication, timesPerDay, quantity  ;
    int month, day, year;

    TextView start;
    Calendar calendar;
    String TAG = "nomedsforyou";

    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_medication);

        nameMedication = findViewById(R.id.nameMedication);
        timesPerDay =findViewById(R.id.timesPerDay);
        quantity = findViewById(R.id.quantity);
        start =findViewById(R.id.start);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        SharedPreferences sharedPreferences = getSharedPreferences("sharing_email", Context.MODE_PRIVATE);
        email = sharedPreferences.getString("email", "email is not available");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String currentDate = sdf.format(calendar.getTime());
        start.setText(currentDate);
    }

    public void setDate(View view) {
        DatePickerDialog dpd = new DatePickerDialog(FormMedication.this ,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, month, dayOfMonth);

                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        String date = sdf.format(selectedDate.getTime());
                        start.setText(date);
                    }

                },day, month, year);

        dpd.getDatePicker().updateDate(year, month, day);
        dpd.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        dpd.show();

    }

    public void saveMedication(View view) {
        Log.d(TAG ,"HASTA ACA TODO BIEN EEEEEEEEH ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            String name = nameMedication.getText().toString();
            if (name.isEmpty()) {
                throw new Exception();
            }
            String times = timesPerDay.getText().toString();
            String quantityValue = quantity.getText().toString();
            LocalDate startValue = LocalDate.parse(start.getText().toString(), formatter);
            int quantityInt = Integer.parseInt(quantityValue);
            int timesInt = Integer.parseInt(times);


            AppDatabase db = Room.databaseBuilder(
                    getApplicationContext(),
                    AppDatabase.class,
                    "dbTest"
            ).allowMainThreadQueries().build();


            Medications medication = new Medications(name, quantityInt, timesInt, startValue, email);
            MedicationsDao medicationsDao = db.medicationsDao();

            medicationsDao.insertMedications(medication);
            finish();
        }
        catch (Exception e){
            Log.d(TAG ,"SOS UN MANCO ESCRIBI BIEN");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Input error")
                    .setMessage("Wrong or missing information, please try again.")
                    .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }});

            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }
}
