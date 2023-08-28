package com.example.glucontrolapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.glucontrolapp.DatabaseUtils.AppDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageView menu;
    LinearLayout home, login , account;

    private static final String TAG = "LogIn";
    TextView tvHourstart;
    Calendar calendar = Calendar.getInstance();
    int year =calendar.get(Calendar.YEAR);
    int month =calendar.get(Calendar.MONTH);
    int day =calendar.get(Calendar.DAY_OF_MONTH);

    String monthString;

    String logged_user;

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
        SharedPreferences sharedPreferences = getSharedPreferences("sharing_email", Context.MODE_PRIVATE);
        logged_user = sharedPreferences.getString("email", "none");

        if (!logged_user.equals("")) {
            Intent intent = new Intent(MainActivity.this, AddRecords.class);
            startActivity(intent);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Login error")
                    .setMessage("Please login to add a new record")
                    .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, LogIn.class);
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

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

        else if (item.getItemId() == R.id.medication_card) {
            Intent intent = new Intent(MainActivity.this, RecyclerViewMedications.class);
            startActivity(intent);

        }

        else if (item.getItemId() == R.id.statistics) {
            Intent intent = new Intent(MainActivity.this, DisplayResultMeasures.class);
            startActivity(intent);

        }

        else if (item.getItemId() == R.id.history) {
            Intent intent = new Intent(MainActivity.this, RecyclerViewMeasures.class);
            startActivity(intent);

        }

        else if (item.getItemId() == R.id.logout) {
            AppDatabase db = Room.databaseBuilder(
                    getApplicationContext(),
                    AppDatabase.class,
                    "dbTest"
            ).allowMainThreadQueries().build();
            db.userDao().LogoutUser(true, false);
            SharedPreferences  sharedPreferences = getSharedPreferences("sharing_email", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email", "" );
            editor.apply();
            Intent intent = new Intent(MainActivity.this, LogIn.class);
            startActivity(intent);

        }

        return true;
    }

    public void addNewMedication(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("sharing_email", Context.MODE_PRIVATE);
        logged_user = sharedPreferences.getString("email", "none");
        Log.d(TAG ,"Estoy en el add medication: " + logged_user);
        if (!logged_user.equals("")) {
            Intent intent = new Intent(MainActivity.this, FormMedication.class);
            startActivity(intent);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Login error")
                    .setMessage("Please login to add a medication.")
                    .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(MainActivity.this, LogIn.class);
                            startActivity(intent);                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}
                    });
            AlertDialog dialog = builder.create();
            dialog.show();
        }

    }
}