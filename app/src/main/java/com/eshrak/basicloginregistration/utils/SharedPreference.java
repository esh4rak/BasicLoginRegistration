package com.eshrak.basicloginregistration.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.eshrak.basicloginregistration.models.User;
import com.google.gson.Gson;


public class SharedPreference {

    private SharedPreferences sharedPref;

    public SharedPreference(Context context) {
        sharedPref = context.getSharedPreferences("User_data_pref", Context.MODE_PRIVATE);
    }


    public void saveLoggedInUser(User user) {
        SharedPreferences.Editor prefsEditor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString("user", json);
        prefsEditor.apply();
    }

    public User retrieveLoggedInUser() {
        Gson gson = new Gson();
        String json = sharedPref.getString("user", "");
        return gson.fromJson(json, User.class);
    }

    public void removeUser() {
        SharedPreferences.Editor prefsEditor = sharedPref.edit();
        prefsEditor.clear().apply();
    }

}
