package com.example.vision;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vision.DB.AppDatabase;
import com.example.vision.DB.GoalDao;

import java.util.HashMap;
import java.util.Map;

public class GoalsPage extends AppCompatActivity {
    private EditText titleEditText, deadlineEditText;
    private Spinner categorySpinner;
    private Button saveButton;
    private GoalDao goalDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goals_screen);

        titleEditText = findViewById(R.id.titleEditText);
        deadlineEditText = findViewById(R.id.deadlineEditText);
        saveButton = findViewById(R.id.button_save);

        goalDao = AppDatabase.getInstance(this).goalDao();

        saveButton.setOnClickListener(v -> saveGoal());
    }

    private void saveGoal() {
        String title = titleEditText.getText().toString();
        String deadline = deadlineEditText.getText().toString();
        String category = categorySpinner.getSelectedItem().toString();
        String description = deadlineEditText.getText().toString();

        Goal goal = new Goal(title, deadline, category, description);
        goalDao.insert(goal);

        Toast.makeText(this, "Goal added!", Toast.LENGTH_SHORT).show();
        finish();
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

