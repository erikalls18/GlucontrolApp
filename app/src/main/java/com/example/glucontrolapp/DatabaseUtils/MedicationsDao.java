package com.example.glucontrolapp.DatabaseUtils;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverters;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Dao
public interface MedicationsDao {

    @TypeConverters({LocalDateConverter.class})
    @Insert
    void insertMedications(Medications...medications);


    @Query("SELECT * FROM medications WHERE email = :email")
    List<Medications> getMedications(String email);


    @TypeConverters({LocalDateConverter.class})
    @Query("UPDATE medications SET nameMedication = :name, quantity =:quantity, timesPerDay =:times, startDate =:date  WHERE email = :email AND id =:id")
    void updateMedications(String name, int quantity, int times, LocalDate date, String email, int id);

    @Query("DELETE  FROM medications WHERE id = :id")
    void deleteMedication(int id);
    @Query("DELETE  FROM medications WHERE email = :email AND id = :id")
    void deleteMedications(String email, int id);

    @Query("DELETE  FROM medications WHERE email = :email")
    void deleteMedicationAll(String email);





}
