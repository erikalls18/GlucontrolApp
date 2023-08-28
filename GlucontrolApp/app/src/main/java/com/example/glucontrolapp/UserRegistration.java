package com.example.glucontrolapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class UserRegistration extends AppCompatActivity {

    EditText name, email, password;
    boolean isLogin = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        email=findViewById(R.id.logEmail);
        name=findViewById(R.id.logName);
        password = findViewById(R.id.logPassword);

    }

    public void singUp(View view) {
        String userEmail = email.getText().toString();
        String userName = name.getText().toString();
        String userPassword = password.getText().toString();



        DatabaseApp db = Room.databaseBuilder(
                getApplicationContext(),
                DatabaseApp.class,
                "dbTest"
        ).allowMainThreadQueries().build();

        // Obtener el DAO de usuario
        DaoUser userDao = db.userDao();



        // Insertar un nuevo usuario en la base de datos
        User user = new User(userEmail, userName, userPassword, isLogin);
        userDao.insertUser(user);

        //User newUser =db.userDao().getUser(userEmail);


        Intent intent = new Intent(UserRegistration.this, MainActivity.class);
        startActivity(intent);

        email.setText("");
        name.setText("");
        password.setText("");
    }

    public void login(View view) {
        Intent intent = new Intent(UserRegistration.this, LogIn.class);
        startActivity(intent);
    }
}