package com.eshrak.basicloginregistration.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.eshrak.basicloginregistration.R;
import com.eshrak.basicloginregistration.databinding.ActivityProfileBinding;
import com.eshrak.basicloginregistration.models.User;
import com.eshrak.basicloginregistration.utils.SharedPreference;


public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        init();

    }


    private void init() {
        setSupportActionBar(binding.actionBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24);
            getSupportActionBar().setTitle("Profile");
            getSupportActionBar().show();
        }

        SharedPreference sharedPreference = new SharedPreference(getApplication().getApplicationContext());
        User user = sharedPreference.retrieveLoggedInUser();

        binding.nameTV.setText(user.getName());
        binding.phoneTV.setText(user.getPhoneNo());
        binding.userNameTV.setText(user.getEmail());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        super.onBackPressed();
        return true;
    }
}