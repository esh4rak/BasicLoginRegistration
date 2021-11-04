package com.eshrak.basicloginregistration.viewmodels;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.eshrak.basicloginregistration.models.User;
import com.eshrak.basicloginregistration.repositories.AuthRepository;
import com.eshrak.basicloginregistration.utils.SharedPreference;


public class SignInViewModel extends AndroidViewModel {

    private final AuthRepository authRepository;
    private SharedPreference sharedPreference;
    private MutableLiveData<Boolean> checkSignIn = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoggedIn = new MutableLiveData<>();

    public SignInViewModel(@NonNull Application application) {
        super(application);
        authRepository = new AuthRepository(application);
        sharedPreference = new SharedPreference(getApplication().getApplicationContext());
    }

    public void signInUser(String email, String pass) {

        try {
            User user = authRepository.loginUser(email, pass);

            if (user != null) {
                sharedPreference.saveLoggedInUser(user);
                checkSignIn.setValue(true);
            } else
                checkSignIn.setValue(false);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public LiveData<Boolean> checkSignIn() {
        return checkSignIn;
    }


    public LiveData<Boolean> isLoggedIn() {

        User user = sharedPreference.retrieveLoggedInUser();

        if (user != null)
            isLoggedIn.setValue(true);
        else
            isLoggedIn.setValue(false);


        return isLoggedIn;
    }
}
