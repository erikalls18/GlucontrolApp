package com.example.glucontrolapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoUser {
    boolean verdadero = true;
    boolean falso = false;
    @Query("SELECT * FROM user WHERE email = :email")
    User getUser(String email);

    @Query("SELECT * FROM user WHERE isLogin = :currentState")
    User getUserLogin(boolean currentState);

    @Query("SELECT * FROM user")
    List<User> getUsers();

    @Insert
    void insertUser(User...users);


    @Query("UPDATE user SET name = :newName WHERE email = :email")
    void updateUser(String email, String newName);

    @Query("UPDATE user SET isLogin = :newState WHERE email = :email")
    void LoginUser(String email, boolean newState);

    @Query("UPDATE user SET isLogin = :newState WHERE isLogin = :currentState")
    void LogoutUser(boolean currentState, boolean newState);
}
