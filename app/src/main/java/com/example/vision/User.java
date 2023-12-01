package com.example.vision;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.vision.DB.AppDatabase;

@Entity(tableName = AppDatabase.USER_TABLE)
public class User {

    @PrimaryKey(autoGenerate = true)
    private int mUserId;
    private boolean mAdmin;
    private String mUserName;
    private String mPassword;

    public User(String userName, String password, boolean admin) {
        mUserName = userName;
        mPassword = password;
        mAdmin = admin;
    }

    public boolean isAdmin() {
        return mAdmin;
    }

    public void setAdmin(boolean admin) {
        mAdmin = admin;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

}

