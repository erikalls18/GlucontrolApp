package com.example.glucontrolapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "LogIn";
    TextView tvHourstart;
    Calendar calendar = Calendar.getInstance();
    int year =calendar.get(Calendar.YEAR);
    int month =calendar.get(Calendar.MONTH);
    int day =calendar.get(Calendar.DAY_OF_MONTH);

    String monthString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHourstart = findViewById(R.id.tvHourstart);

        SimpleDateFormat sdf = new SimpleDateFormat("MMMM", Locale.getDefault());
        monthString = sdf.format(calendar.getTime());


        String date = String.valueOf(day) + " " + monthString + " " + String.valueOf(year);
        tvHourstart.setText( date );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void addNewRecord(View view) {
        Intent intent = new Intent(MainActivity.this, AddRecords.class);
        startActivity(intent);


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.singIn) {
            Intent intent = new Intent(MainActivity.this, LogIn.class);
            startActivity(intent);

        }
        else if (item.getItemId() == R.id.account) {
            Intent intent = new Intent(MainActivity.this, UserRegistration.class);
            startActivity(intent);

        }

        else if (item.getItemId() == R.id.medication) {
            Intent intent = new Intent(MainActivity.this, FormMedication.class);
            startActivity(intent);

        }

        /*else if (item.getItemId() == R.id.advices) {
            Intent intent = new Intent(MainActivity.this, UserRegistration.class);
            startActivity(intent);

        }*/

        else if (item.getItemId() == R.id.logout) {
            DatabaseApp db = Room.databaseBuilder(
                    getApplicationContext(),
                    DatabaseApp.class,
                    "dbTest"
            ).allowMainThreadQueries().build();
            db.userDao().LogoutUser(true, false);
            Intent intent = new Intent(MainActivity.this, LogIn.class);
            startActivity(intent);

        }

        return true;
    }

    public void addNewMedication(View view) {
        Intent intent = new Intent(MainActivity.this, FormMedication.class);
        startActivity(intent);
    }
}