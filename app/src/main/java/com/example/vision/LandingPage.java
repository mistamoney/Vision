package com.example.vision;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class LandingPage extends AppCompatActivity {
    private Button mAdminBtn;
    private static final String LANDING_PAGE_USERNAME = "com.example.myapplication.LANDING_PAGE_USERNAME";

    private String value;
    private TextView mUsername;
    private static String adminCheck = "com.example.myapplication.adminCheck";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        String userName = getIntent().getStringExtra(LANDING_PAGE_USERNAME);

        mAdminBtn = findViewById(R.id.btnAdminArea);
        mUsername = findViewById(R.id.tvWelcome);
        mUsername.setText("Welcome " + userName);
        value = getIntent().getStringExtra(adminCheck);

        checkUserIsAdmin();

        displayCalendar();

        Button mGoalsButton = findViewById(R.id.your_goals);
        mGoalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandingPage.this, GoalsPage.class);
                startActivity(intent);
            }
        });

        Button mProgressButton = findViewById(R.id.your_progress);
        mProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LandingPage.this, ProgressPage.class);
                startActivity(intent);
            }
        });
    }

    public void checkUserIsAdmin() {
        if(value != null && value.equalsIgnoreCase("true")) {
            mAdminBtn.setVisibility(View.VISIBLE);
            mAdminBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LandingPage.this, AdminZone.class);
                    startActivity(intent);
                }
            });
        } else {
            mAdminBtn.setVisibility(View.INVISIBLE);
        }
    }

    public void displayCalendar() {
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        TextView textViewDate = findViewById(R.id.text_view_date);
        textViewDate.setText(currentDate);
    }

    public static Intent intentFactory(Context context, int userId, String admin, String userName){
        Intent intent = new Intent(context, LandingPage.class);
        intent.putExtra(LANDING_PAGE_USERNAME, userName);
        intent.putExtra(adminCheck ,admin);
        intent.putExtra("user_id", userId);
        return intent;
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
