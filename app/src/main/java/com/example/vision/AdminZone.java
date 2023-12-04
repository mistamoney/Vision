package com.example.vision;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vision.DB.AppDatabase;
import com.example.vision.DB.UserDao;

import java.util.HashMap;
import java.util.Map;

public class AdminZone extends AppCompatActivity {
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_zone);

        Button btnDeleteUser = findViewById(R.id.btnDeleteUsers);

        // Set a click listener to navigate to the DeleteUser activity
        btnDeleteUser.setOnClickListener(view -> {
            Intent intent = new Intent(AdminZone.this, DeleteUser.class);
            startActivity(intent);
        });

        // Initialize the database
        initializeDatabase();
    }

    private void initializeDatabase() {
        // Initialize Room database
        userDao = AppDatabase.getInstance(this).userDao();
    }

    public static Intent intentFactory(Context context) {
        return new Intent(context, AdminZone.class);
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
