package com.example.glucontrolapp;

import static com.example.glucontrolapp.R.menu.menu_main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class SecondMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }


   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(menu_main,menu);
       Log.d("MainActivity", "onCreateOptionsMenu called");
       return true;
   }

    public void singIn(View view) {
        Intent intent = new Intent(SecondMainActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onTextViewClick(View view) {
        Intent intent = new Intent(SecondMainActivity.this, UserRegistration.class);
        startActivity(intent);
    }

}