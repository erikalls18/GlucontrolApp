package com.example.glucontrolapp.DatabaseUtils;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.glucontrolapp.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(foreignKeys = {@ForeignKey(
        entity = Users.class,
        parentColumns = "email",
        childColumns = "email",
        onDelete = ForeignKey.CASCADE

)})
public class Medications {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    public int  id ;

    @NonNull
    @ColumnInfo (name = "nameMedication")
    String nameMedication;

    @NonNull
    @ColumnInfo(name= "quantity")
    double quantity;

    @NonNull
    @ColumnInfo(name ="timesPerDay")
    int timesPerDay;

    @NonNull
    @ColumnInfo(name ="startDate")
    @TypeConverters({LocalDateConverter.class})
    LocalDate startDate;

    @NonNull
    @ColumnInfo(name= "email")
    public String email;

    public Medications(){}

    public Medications(String nameMedication, double quantity, int timesPerDay , LocalDate startDate, String  email){
        this.nameMedication= nameMedication;
        this.quantity = quantity;
        this.timesPerDay = timesPerDay;
        this.startDate =startDate;
        this.email = email;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getNameMedication() {
        return nameMedication;
    }

    public void setNameMedication(@NonNull String nameMedication) {
        this.nameMedication = nameMedication;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getTimesPerDay() {
        return timesPerDay;
    }

    public void setTimesPerDay(@NonNull int timesPerDay) {
        this.timesPerDay = timesPerDay;
    }



    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(@NonNull LocalDate startDate) {
        this.startDate = startDate;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }
}
