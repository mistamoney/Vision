package com.example.vision;

import android.app.Application;
import com.example.vision.DB.AppDatabase;
import com.example.vision.DB.UserDao;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppRepository {
    private UserDao mUserDao;
    private List<User> mAllUsers;
    private static final int NUMBER_OF_THREADS = 4;
    private static final ExecutorService databaseWriteExecuter = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    AppRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        mUserDao = db.userDao();
        mAllUsers = mUserDao.getAllUsers();
    }

    List<User> getAllUsers() {
        return mAllUsers;
    }

    void insert(User user) {
        databaseWriteExecuter.execute(() -> { mUserDao.insert(user);
        });
    }

    public User getUser(String username) {
        return mUserDao.getUserByUserName(username);
    }
}
