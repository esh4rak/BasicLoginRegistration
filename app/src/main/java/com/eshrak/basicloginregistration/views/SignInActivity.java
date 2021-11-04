package com.eshrak.basicloginregistration.views;

import static com.eshrak.basicloginregistration.utils.KeyBoard.hideKeyboard;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.eshrak.basicloginregistration.databinding.ActivitySignInBinding;
import com.eshrak.basicloginregistration.viewmodels.SignInViewModel;
import com.google.android.material.snackbar.Snackbar;


public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding binding;
    private SignInViewModel signInViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        signInViewModel = new ViewModelProvider(this).get(SignInViewModel.class);

        init();

    }

    private void init() {

        binding.signUpButton.setOnClickListener(view -> startActivity(new Intent(SignInActivity.this, SignUpActivity.class)));


        binding.signInButton.setOnClickListener(view ->
        {
            hideKeyboard(this);

            String email = binding.emailET.getText().toString().trim();
            String password = binding.passwordET.getText().toString().trim();

            signInViewModel.signInUser(
                    email,
                    password
            );

            signInViewModel.checkSignIn().observe(this, aBoolean -> {
                if (aBoolean) {
                    goToDashBoard();
                } else {
                    Snackbar.make(binding.getRoot(), "Account not found", Snackbar.LENGTH_LONG).show();
                }
            });


        });

    }

    private void goToDashBoard() {
        Intent intent = new Intent(SignInActivity.this, DashboardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}