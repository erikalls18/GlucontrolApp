package com.example.glucontrolapp.DatabaseUtils;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.glucontrolapp.User;

import java.util.List;

@Dao
public interface UserDao {
    boolean verdadero = true;
    boolean falso = false;
    @Query("SELECT * FROM users WHERE email = :email")
    Users getUser(String email);

    @Query("SELECT * FROM users WHERE isLogin = :currentState")
    Users getUserLogin(boolean currentState);

    /*@Query("SELECT * FROM users")
    List<Users> getUsers();*/

    @Insert
    void insertUser(Users...users);


    @Query("UPDATE users SET name = :newName WHERE email = :email")
    void updateUser(String email, String newName);

    @Query("UPDATE users SET isLogin = :newState WHERE email = :email")
    void LoginUser(String email, boolean newState);

    @Query("UPDATE users SET isLogin = :newState WHERE isLogin = :currentState")
    void LogoutUser(boolean currentState, boolean newState);
}

