package com.eshrak.basicloginregistration.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;


import com.eshrak.basicloginregistration.databinding.ActivitySplashBinding;
import com.eshrak.basicloginregistration.viewmodels.SignInViewModel;
import com.github.ybq.android.spinkit.style.ChasingDots;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;

    private SignInViewModel signInViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();
    }


    private void init() {

        ChasingDots chasingDots = new ChasingDots();
        binding.spinKitView.setIndeterminateDrawable(chasingDots);


        int SPLASH_DISPLAY_LENGTH = 3000;
        new Handler().postDelayed(() -> {
            initSplashViewModel();
            checkIfUserIsAuthenticated();
        }, SPLASH_DISPLAY_LENGTH);

    }

    private void initSplashViewModel() {

        signInViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.
                getInstance(this.getApplication())).get(SignInViewModel.class);
    }

    private void checkIfUserIsAuthenticated() {

        signInViewModel.isLoggedIn().observe(this, aBoolean -> {
            Intent mainIntent;
            if (aBoolean) {
                mainIntent = new Intent(SplashActivity.this, DashboardActivity.class);
            } else {
                mainIntent = new Intent(SplashActivity.this, SignInActivity.class);
            }
            startActivity(mainIntent);
            finish();
        });
    }
}