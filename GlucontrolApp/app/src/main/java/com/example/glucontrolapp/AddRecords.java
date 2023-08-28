package com.example.glucontrolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.media.Image;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddRecords extends AppCompatActivity  {

    TextView tvDate, tvHour;
    Calendar calendar = Calendar.getInstance();
    String monthString;
    int year =calendar.get(Calendar.YEAR);
    int month =calendar.get(Calendar.MONTH);
    int day =calendar.get(Calendar.DAY_OF_MONTH);

    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minutes= calendar.get(Calendar.SECOND);
    int seconds = calendar.get(Calendar.SECOND);

    int measure =80;

    private ImageView btnIncrease , btnDecrease;

    EditText edMeasure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_records);

        tvDate =findViewById(R.id.tvDate);
        tvHour =findViewById(R.id.tvHour);
        edMeasure =findViewById(R.id.edMeasure);
        btnIncrease = findViewById(R.id.btnIncrease);
        btnDecrease = findViewById(R.id.btnDecrease);

        btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                measure ++;
                edMeasure.setText(String.valueOf(measure));
            }
        });

        btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                measure ++;
                edMeasure.setText(String.valueOf(measure));
            }
        });



        SimpleDateFormat sdf = new SimpleDateFormat("MMMM", Locale.getDefault());
        monthString = sdf.format(calendar.getTime());



        String date = String.valueOf(day) + " " + monthString + " " + String.valueOf(year);
        tvDate.setText( date );
        String hourString = String.valueOf(hour ) + ":" + String.valueOf(minutes)   ;
        String formatedHour =formatTime(hour, minutes);
        tvHour.setText(formatedHour );





    }

    public void showHour(View view) {

        TimePickerDialog timePicker = new TimePickerDialog(AddRecords.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String hour = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
                String formattedTime = formatTime(hourOfDay, minute);
                tvHour.setText(formattedTime );
            }
        }, hour, minutes, false);
        timePicker.show();
    }

    private String formatTime(int hourOfDay, int minute) {
        String formattedTime;
        String amPm;

        if (hourOfDay >= 12) {
            amPm = "PM";
            if (hourOfDay > 12) {
                hourOfDay -= 12;
            }
        } else {
            amPm = "AM";
            if (hourOfDay == 0) {
                hourOfDay = 12;
            }
        }

        formattedTime = String.format(Locale.getDefault(), "%02d:%02d %s", hourOfDay, minute, amPm);
        return formattedTime;
    }

    public void showDate(View view) {
        DatePickerDialog dpd =new DatePickerDialog(AddRecords.this ,
                new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //String date = dayOfMonth + "/" + month + "/" + year;
                //tvDate.setText(date);
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String date = sdf.format(selectedDate.getTime());
                tvDate.setText(date);
            }

        },day, month, year);

        dpd.getDatePicker().updateDate(year, month, day);
        dpd.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        dpd.show();
    }


}