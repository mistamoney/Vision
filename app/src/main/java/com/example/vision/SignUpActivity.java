package com.example.vision;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.vision.DB.AppDatabase;
import com.example.vision.DB.UserDao;

public class SignUpActivity extends AppCompatActivity {
    private EditText editTextRegisterUser;
    private EditText passwordRegister1;
    private EditText passwordRegister2;

    private UserDao mUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button mHomePage = findViewById(R.id.homePageButton);
        editTextRegisterUser = findViewById(R.id.usernameSignUp);
        passwordRegister1 = findViewById(R.id.passwordSignUp1);
        passwordRegister2 = findViewById(R.id.passwordSignUp2);

        getDataBase();

        Button signUpButton = findViewById(R.id.signUpButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = editTextRegisterUser.getText().toString().trim();
                String passWord1 = passwordRegister1.getText().toString().trim();
                String passWord2 = passwordRegister2.getText().toString().trim();

                registerUser(userName, passWord1, passWord2);
            }
        });

        mHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public static Intent intentFactory(Context context) {
        Intent intent = new Intent(context, SignUpActivity.class);
        return intent;
    }

    private void registerUser(String user, String pass1, String pass2) {
        User existingUser = mUserDao.getUserByUserName(user);
        if (existingUser == null) {
            if (pass1.equals(pass2)) {
                User u = new User(user, pass1, false);
                mUserDao.insert(u);
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Passwords Don't Match!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "USERNAME NOT AVAILABLE", Toast.LENGTH_SHORT).show();
        }
    }

    private void getDataBase() {
        mUserDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .userDao();
    }
}
