package com.example.glucontrolapp.DatabaseUtils;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    /*@TypeConverter
    public static LocalDate fromTimestamp(String value) {
        return value == null ? null : LocalDate.parse(value, formatter);
    }*/

    @TypeConverter
    public static LocalDate fromTimestamp(String value) {
        return value == null ? null : LocalDate.parse(value);
    }

    @TypeConverter
    public static String dateToTimestamp(LocalDate date) {
        return date == null ? null : date.format(formatter);
    }
}

