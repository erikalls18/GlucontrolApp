package com.example.glucontrolapp.DatabaseUtils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.glucontrolapp.DatabaseApp;


@Database(entities = {Users.class, Measures.class, Medications.class},
            version = 1,
            exportSchema= false
    )

public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract MeasuresDao measuresDao();

    public abstract MedicationsDao medicationsDao();

    private static DatabaseApp INSTANCE;

    public static DatabaseApp getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (DatabaseApp.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DatabaseApp.class, "user_database")
                            .build();
                }
            }
        }
        return INSTANCE;


    }

}