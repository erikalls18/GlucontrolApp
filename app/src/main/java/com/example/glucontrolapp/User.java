package com.example.glucontrolapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity
public class User {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name= "email")
    public String email;

    @NonNull
    @ColumnInfo(name= "name")
    public String name;
    @NonNull
    @ColumnInfo(name= "password")
    public String password;



    public boolean isLogin ;

    public User(@NonNull String email, String name, String password, boolean isLogin){
        this.name = name;
        this.email = email;
        this.password = password;
        this.isLogin = isLogin;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

   public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
