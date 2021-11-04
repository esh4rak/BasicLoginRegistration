package com.eshrak.basicloginregistration.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.eshrak.basicloginregistration.models.User;
import com.eshrak.basicloginregistration.repositories.AuthRepository;

public class SignUpViewModel extends AndroidViewModel {

    private AuthRepository authRepository;
    private MutableLiveData<Boolean> isAlreadyExist = new MutableLiveData<>();

    public SignUpViewModel(@NonNull Application application) {
        super(application);
        authRepository = new AuthRepository(application);
    }

    public void registerUser(User user) throws Exception {
        authRepository.registerUser(user);
    }


    public LiveData<Boolean> isExist(String email) throws Exception {

        isAlreadyExist.setValue(authRepository.CheckUser(email));

        return isAlreadyExist;
    }

}
