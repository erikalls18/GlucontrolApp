package com.example.glucontrolapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.glucontrolapp.DatabaseUtils.LocalDateConverter;

@Database(entities = {User.class},
        version = 1,
        exportSchema= false
)
@TypeConverters({LocalDateConverter.class})
public abstract class DatabaseApp extends RoomDatabase {
    public abstract  DaoUser userDao();
    private static DatabaseApp INSTANCE;

   /* public static DatabaseApp getDatabase(final Context context) {

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
    }*/


}


