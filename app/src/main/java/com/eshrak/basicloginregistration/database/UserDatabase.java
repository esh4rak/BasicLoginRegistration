package com.eshrak.basicloginregistration.database;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.eshrak.basicloginregistration.models.User;

@Database(entities = User.class, exportSchema = false, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    public static UserDatabase userDB;

    public abstract UserDao userDao();

    public static final String DB_NAME = "user_db";

    public static synchronized UserDatabase userDB(Context context) {
        if (userDB == null) {
            userDB = Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return userDB;
    }


    private static final RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };


}
