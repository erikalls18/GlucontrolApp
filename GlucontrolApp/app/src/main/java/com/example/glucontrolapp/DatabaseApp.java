package com.example.glucontrolapp;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},
        version = 1
)
public abstract class DatabaseApp extends RoomDatabase {
    public abstract  DaoUser userDao();
}

