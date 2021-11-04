package com.eshrak.basicloginregistration.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.eshrak.basicloginregistration.models.User;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("select * from User where email = :email AND password = :password ")
    User findUser(String email, String password);

    @Query("select * from User where email = :email ")
    User findUserByEmail(String email);

}
