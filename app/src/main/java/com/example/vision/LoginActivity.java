package com.example.vision;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class LoginActivity extends AppCompatActivity{
    private EditText myUsername;
    private EditText myPassword;
    private Button buttonLogin;
    private UserViewModel userViewModel;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("VisionPreferences", Context.MODE_PRIVATE);

        myUsername = findViewById(R.id.etUsername);
        myPassword = findViewById(R.id.etPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
    }

    private void attemptLogin() {
        String username = myUsername.getText().toString();
        String password = myPassword.getText().toString();

        userViewModel.getUser(username).observe(this, user -> {
            if (user != null && user.getPassword().equals(password)) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", true);
                editor.putString("username", username);
                editor.apply();

                Intent intent = new Intent(LoginActivity.this, LandingPage.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Invalid User!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
