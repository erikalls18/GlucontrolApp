package com.example.glucontrolapp.DatabaseUtils;

import android.icu.util.Measure;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;

import com.example.glucontrolapp.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Dao
public interface MeasuresDao {

    @Query("SELECT * FROM measures WHERE email = :email")
    List<Measures> getMeasures(String email);
    //Measures getMeasure(String email);

    @Query("SELECT * FROM measures WHERE email = :email")
    Measures getMeasure(String email);
    @TypeConverters({LocalDateConverter.class})
    @Query("SELECT * FROM measures WHERE email = :email AND date >= date('now', '-7 days')")
    List<Measures>  getMeasuresLast7Days(String email );

    @TypeConverters({LocalDateConverter.class})
    @Query("SELECT * FROM measures WHERE email = :email AND date >= date('now', '-7 days')")
    List<Measures> getMeasuresLast15Days(String email );

    @TypeConverters({LocalDateConverter.class})
    @Query("SELECT * FROM measures WHERE email = :email AND date >= date('now', '-7 days')")
    List<Measures> getMeasuresLast30Days(String email);

   @Query("SELECT MAX(measure) FROM measures WHERE email = :email")
    double getMaximMeasure(String email);

    @Query("SELECT MIN(measure) FROM measures WHERE email = :email")
    double getMinimumMeasure(String email);

    @Query("SELECT AVG(measure) FROM measures WHERE email = :email")
    double getAverageMeasure(String email);


    @TypeConverters({LocalDateConverter.class})
    @Insert
    void insertMeasures(Measures...measures);


    @Query("UPDATE measures SET measure = :measure  WHERE email = :email AND id =:id")
    void updateMeasure(String email, double measure, int id);

    @Query("DELETE  FROM measures WHERE  id = :id")
    void deleteMeasures( int id);

    @Query("DELETE  FROM measures WHERE email = :email")
    void deleteMeasuresAll(String email);


}
