package com.example.glucontrolapp.DatabaseUtils;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.glucontrolapp.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(foreignKeys = {@ForeignKey(
        entity = Users.class,
        parentColumns = "email",
        childColumns = "email",
        onDelete = ForeignKey.CASCADE
)})

    public class Measures implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    //@ColumnInfo(name = "id")
    public int  id ;

   @ColumnInfo(name="date")
   @NonNull

   @TypeConverters({LocalDateConverter.class})
    public LocalDate date;

    @ColumnInfo(name="hour")
   @NonNull
    public String hour;

   @NonNull
   @ColumnInfo(name= "measure")
    public double  measure;

    @NonNull
    @ColumnInfo(name= "email")
    public String email;

    public  Measures() {

    }

    public  Measures(@NonNull double  measure, LocalDate date, String hour, String email ){
        this.measure= measure;
        this.date = date;
        this.hour =hour;
        this.email = email;
    }

    public Measures(@NonNull double measure, String email, int id){
        this.measure= measure;
        this.email = email;
        this.id =id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public LocalDate getDate() {
        return date;
    }

    public void setDate(@NonNull LocalDate date) {
        this.date = date;
    }

    public double getMeasure() {
        return measure;
    }

    public void setMeasure(double measure) {
        this.measure = measure;
    }

    @NonNull
    public String getUser_email() {
        return email;
    }

    public void setUser_email(@NonNull String user_email) {
        this.email = user_email;
    }


    public String getHour() {
        return hour;
    }

    public void setHour( String hour) {
        this.hour = hour;
    }
}
