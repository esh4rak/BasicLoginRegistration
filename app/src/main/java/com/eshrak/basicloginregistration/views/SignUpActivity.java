package com.eshrak.basicloginregistration.views;

import static com.eshrak.basicloginregistration.utils.KeyBoard.hideKeyboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.eshrak.basicloginregistration.databinding.ActivitySignUpBinding;
import com.eshrak.basicloginregistration.models.User;
import com.eshrak.basicloginregistration.viewmodels.SignUpViewModel;
import com.google.android.material.snackbar.Snackbar;


public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private SignUpViewModel signUpViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        init();

    }

    private void init() {

        binding.signInButton.setOnClickListener(view -> {

            Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        });


        binding.signUpButton.setOnClickListener(view ->
                {
                    try {
                        registerUser();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );

    }


    public void registerUser() throws Exception {

        hideKeyboard(this);

        signUpViewModel.isExist(binding.emailET.getText().toString().trim()).observe(this, aBoolean -> {
            if (aBoolean) {
                Snackbar.make(binding.getRoot(), "User already registered by this email", Snackbar.LENGTH_LONG).show();
            } else {

                if (!binding.nameET.getText().toString().trim().isEmpty() &&
                        !binding.phoneET.getText().toString().trim().isEmpty() &&
                        !binding.emailET.getText().toString().trim().isEmpty() &&
                        !binding.passwordET.getText().toString().trim().isEmpty()) {

                    User user = new User(
                            binding.nameET.getText().toString().trim(),
                            binding.phoneET.getText().toString().trim(),
                            binding.emailET.getText().toString().trim(),
                            binding.passwordET.getText().toString().trim()
                    );

                    try {
                        signUpViewModel.registerUser(user);

                        Snackbar.make(binding.getRoot(), "Registration Successful", Snackbar.LENGTH_LONG).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    gotoSignIn();

                } else {
                    Snackbar.make(binding.getRoot(), "Fill Up All the Fields", Snackbar.LENGTH_LONG).show();
                }

            }
        });


    }

    public void gotoSignIn() {
        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}