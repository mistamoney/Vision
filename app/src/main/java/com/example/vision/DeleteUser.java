package com.example.vision;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.vision.DB.AppDatabase;
import com.example.vision.DB.UserDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteUser extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private UserAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private UserDao mUserDao;

    List<User> users;
    ArrayList<UserItem> userList = new ArrayList<>();

    public void removeItem(int position) {
        String username = userList.get(position).getUsername();
        userList.remove(position);
        User user = mUserDao.getUserByUserName(username);
        mUserDao.delete(user);
        Toast.makeText(this, "User deleted", Toast.LENGTH_SHORT).show();
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_user_page);

        initializeDatabase();
        List<User> users = mUserDao.getAllUsers();
        for (User u : users) {
            userList.add(new UserItem(R.drawable.ic_android, u.getUserName()));
        }

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new UserAdapter(userList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }

    private void initializeDatabase() {
        mUserDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
                .userDao();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Map<Integer, Class<?>> menuActions = new HashMap<>();
        menuActions.put(R.id.login_option, LoginActivity.class);
        menuActions.put(R.id.home_option, LandingPage.class);
        menuActions.put(R.id.main_option, MainActivity.class);

        Class<?> activityClass = menuActions.get(item.getItemId());
        if (activityClass != null) {
            startActivity(new Intent(this, activityClass));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

