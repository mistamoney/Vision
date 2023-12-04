package com.example.vision;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vision.DB.AppDatabase;
import com.example.vision.DB.UserDao;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity{
    private EditText mUsername;
    private EditText mPassword;
    static final String LANDING_PAGE_USERNAME = "com.example.myapplication.LANDING_PAGE_USERNAME";
    private UserDao mUserDao;
    private String mUsernameString;
    private String mPasswordString;
    private User mUser;
    private String mAdminStats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getDataBase();

        wireupDisplay();

    }


    private void wireupDisplay(){
        mUsername = findViewById(R.id.etUsername);
        mPassword = findViewById(R.id.etPassword);

        Button button = findViewById(R.id.buttonLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValuesFromDisplay();
                if (checkForUserInDatabase()){
                    if (!validatePassword()){
                        Toast.makeText(LoginActivity.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                    } else {

                        Intent intent = LandingPage.intentFactory(getApplicationContext(), mUser.getUserId(), mAdminStats, mUsernameString);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    private boolean validatePassword(){
        return mUser.getPassword().equals(mPasswordString);
    }

    private void getValuesFromDisplay(){
        mUsernameString = mUsername.getText().toString();
        mPasswordString = mPassword.getText().toString();
    }

    private boolean checkForUserInDatabase(){
        mUser = mUserDao.getUserByUserName(mUsernameString);
        if (mUser == null){
            Toast.makeText(this, "no user " + mUsernameString + " found", Toast.LENGTH_SHORT).show();
            return false;
        }
        mAdminStats = mUser.isAdmin() + "";
        return true;
    }

    private void getDataBase(){
        mUserDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .userDao();
        if (mUserDao.getAllUsers().size() <= 0) {
            User admin2 = new User("admin2", "admin2", true);
            mUserDao.insert(admin2);
            User testUser1 = new User("testUser1", "testUser1", false);
            mUserDao.insert(testUser1);
        }
    }

    public static Intent intentFactory(Context context, int userId, String userName){
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("user_id", userId);
        intent.putExtra(LANDING_PAGE_USERNAME, userName);
        return intent;
    }
}
