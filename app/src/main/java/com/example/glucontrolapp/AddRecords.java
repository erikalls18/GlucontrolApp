package com.example.glucontrolapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.glucontrolapp.DatabaseUtils.AppDatabase;
import com.example.glucontrolapp.DatabaseUtils.Measures;
import com.example.glucontrolapp.DatabaseUtils.MeasuresDao;
import com.example.glucontrolapp.DatabaseUtils.Users;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;

public class AddRecords extends AppCompatActivity  {

    TextView tvDate, tvHour, tvMessage;
    Calendar calendar = Calendar.getInstance();
    String monthString;
    int year =calendar.get(Calendar.YEAR);
    int month =calendar.get(Calendar.MONTH);
    int day =calendar.get(Calendar.DAY_OF_MONTH);

    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minutes= calendar.get(Calendar.SECOND);
    int seconds = calendar.get(Calendar.SECOND);

    double measure = 80;

    private ImageView btnIncrease , btnDecrease;

    EditText edMeasure;

    Button btnSaveMeasures;

    String date;

    String  formattedTime;

    LocalDate localDateNow;

    String current_login;

    double current_measure;

    private static final String TAG = "AddRecords";
    boolean isTimeSelected = false;

    private String message1;

    private String message2;
    private CheckBox checkBox1, checkBox2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_records);

        tvDate =findViewById(R.id.tvDate);
        tvHour =findViewById(R.id.tvHour);
        edMeasure =findViewById(R.id.edMeasure);
        btnIncrease = findViewById(R.id.btnIncrease);
        btnDecrease = findViewById(R.id.btnDecrease);
        btnSaveMeasures = findViewById(R.id.btn_saveMeasures);
        tvMessage= findViewById(R.id.tvMessage);

        message1 ="Your levels of sugar are within normal ranges ";
        message2 = "Watch out! Your levels of sugar have increased";

        checkBox1= findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);

        //current_measure = Double.parseDouble(edMeasure.getText().toString());


        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    double measureValue = Double.parseDouble(edMeasure.getText().toString());
                    current_measure = measureValue;
                    Log.d(TAG, "currentm-meaasure: " + current_measure);
                            checkBox2.setChecked(false);
                    if(measureValue>=80 && current_measure<=130){
                        tvMessage.setText(message1);
                    }
                    else{
                        tvMessage.setText(message2);
                    }

                }
            }
        });

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    double measureValue = Double.parseDouble(edMeasure.getText().toString());
                    current_measure = measureValue;

                    checkBox1.setChecked(false);
                    if( current_measure<180){
                        tvMessage.setText(message1);
                    }
                    else{
                        tvMessage.setText(message2);
                    }

                }
            }
        });



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
                measure --;
                edMeasure.setText(String.valueOf(measure));
            }
        });


        SimpleDateFormat sdf = new SimpleDateFormat("MMMM", Locale.getDefault());
        monthString = sdf.format(calendar.getTime());


        String date = String.valueOf(day) + " " + monthString + " " + String.valueOf(year);
        tvDate.setText( date );
        String hourString = String.valueOf(hour ) + ":" + String.valueOf(minutes)   ;
        String formatedHour = formatTime(hour, minutes);
        tvHour.setText(formatedHour );
        SharedPreferences sharedPreferences = getSharedPreferences("sharing_email", Context.MODE_PRIVATE);
        current_login = sharedPreferences.getString("email", "email is not available");
        Log.d(TAG ,"este es un mensaje de las shared en la ADD RECORDS JAJAJAJAJA oh si: " + sharedPreferences.getString("email", "email is not available"));
    }

    public void showHour(View view) {

        TimePickerDialog timePicker = new TimePickerDialog(AddRecords.this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                isTimeSelected = true;
                String hour = String.valueOf(hourOfDay) + ":" + String.valueOf(minute);
                formattedTime = formatTime(hourOfDay, minute);
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
                date = sdf.format(selectedDate.getTime());
                tvDate.setText(date);
            }

        },day, month, year);

        dpd.getDatePicker().updateDate(year, month, day);
        dpd.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        dpd.show();
    }


    public void saveMeasure(View view) {

       /// ViewModelGeneral myViewModel = new ViewModelProvider(this).get(ViewModelGeneral.class);
        Intent current_intent = getIntent();
        String user_email = current_intent.getStringExtra("email");
        Log.d(TAG , "este es un mensaje LALALALA: " + user_email);
        SharedPreferences  sharedPreferences = getSharedPreferences("sharing_email", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "email is not available");
        localDateNow = LocalDate.now();

        if(isTimeSelected ==false){
            formattedTime = hour + ":" + minutes;
        }

        AppDatabase db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbTest"
        ).allowMainThreadQueries().build();

        //DaoUser userDao = db.userDao();

        Users user= new Users();
        String emailUser= user.getEmail();
        current_measure = Double.parseDouble(edMeasure.getText().toString());
        //Log.d(TAG, "current_measure + "current_measure
                Measures newMeasure1 = new Measures(current_measure, localDateNow, formattedTime, current_login);

        MeasuresDao measuresDao = db.measuresDao();
        measuresDao.insertMeasures(newMeasure1);

        Log.d(TAG , "este es un mensaje" + localDateNow);
        finish();
    }
}