package com.example.glucontrolapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.glucontrolapp.DatabaseUtils.AppDatabase;
import com.example.glucontrolapp.DatabaseUtils.Users;

import java.util.List;

public class LogIn extends AppCompatActivity {

    EditText  email, password;
    TextView noUser;
    private static final String TAG = "LogIn";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        email= findViewById(R.id.logEmail);
        password = findViewById(R.id.logPassword);
        noUser = findViewById(R.id.tvNoUser);


    }

    public void registerNewUser(View view) {
        Intent intent = new Intent(LogIn.this, UserRegistration.class);
        startActivity(intent);
    }

    public void singIn(View view) {
        String message = "The email or password is invalid";
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();

        /*Default User*/
       /* String defaultUser = "user@mail.com";
        String defaultPassword = "password";
        String defaultName = "user";*/

        List<User> userList;


        AppDatabase db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbTest"
        ).allowMainThreadQueries().build();

        //DaoUser userDao = db.userDao();

       /* Users defaultAccount = db.userDao().getUser(defaultUser);
       if (defaultAccount == null) {
            Users setupUser = new Users(defaultUser, defaultName, defaultPassword, true);
            db.userDao().insertUser(setupUser);
        }*/

        Users newUser = db.userDao().getUser(userEmail);
        if (newUser != null) {
            //newUser.getPassword();
            String passwordSaved = newUser.getPassword();

            if (passwordSaved.equals(userPassword)) {
                SharedPreferences sharedPreferences = getSharedPreferences("sharing_email", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("email", userEmail );
                editor.apply();

                email.setText("");
                password.setText("");
                db.userDao().LoginUser(userEmail, true);
                Intent intent = new Intent(LogIn.this, MainActivity.class);
                startActivity(intent);

            } else {
                noUser.setText(message);

            }
        } else {
            noUser.setText(message);


        }

    }

    }

