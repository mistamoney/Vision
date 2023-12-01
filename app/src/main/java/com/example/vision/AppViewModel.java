package com.example.vision;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class AppViewModel extends AndroidViewModel {
    private AppRepository mRepository;
    private final List<User> mAllUsers;
    public AppViewModel(@NonNull Application application) {
        super(application);
        mRepository = new AppRepository(application);
        mAllUsers = mRepository.getAllUsers();
    }

    List<User> getAllUser() {
        return mAllUsers;
    }

    public void insert(User user) {
        mRepository.insert(user);
    }

    public User getUser(String username) {
        return mRepository.getUser(username);
    }
}
