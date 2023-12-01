package com.example.vision;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Calendar;

public class LandingPage extends AppCompatActivity {
    private static final String LANDING_PAGE_USERNAME = "com.example.vision.LANDING_PAGE_USERNAME";
    private static final String PREFS_NAME = "com.example.vision.PrefsFile";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        // Initialize your TextView and Buttons
        TextView textViewWelcome = findViewById(R.id.tvWelcome);
        Button btnLogout = findViewById(R.id.btnLogout);
        Button btnAdminArea = findViewById(R.id.btnAdminArea);

        // Set up the logout button
        btnLogout.setOnClickListener(view -> logout());

        // Display the date
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        TextView textViewDate = findViewById(R.id.text_view_date);
        textViewDate.setText(currentDate);

        // Retrieve the username from the intent or SharedPreferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String username = prefs.getString(LANDING_PAGE_USERNAME, null);
        if (username == null) {
            // Fallback: If username is not found in SharedPreferences, get it from the intent
            username = getIntent().getStringExtra(LANDING_PAGE_USERNAME);
        }

        // Set the welcome message with the username
        if (username != null && !username.isEmpty()) {
            textViewWelcome.setText(getString(R.string.welcome_user, username));
        } else {
            textViewWelcome.setText(getString(R.string.welcome_user, "User")); // Default to "User" if username is not provided
        }

        // Check if the user is an admin and show the admin area button if true
        String adminStatus = getIntent().getStringExtra("adminCheck");
        if ("admin".equals(adminStatus)) {
            btnAdminArea.setVisibility(View.VISIBLE);
        }
    }

    private void logout() {
        // Clear the user's session and navigate back to the MainActivity
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear(); // This will clear all the data in SharedPreferences
        editor.apply();

        // Start the MainActivity and clear the back stack
        Intent intent = new Intent(LandingPage.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); // Call this when your activity is done and should be closed
    }

    public static Intent intentFactory(Context context, int userId, String admin, String userName) {
        Intent intent = new Intent(context, LandingPage.class);
        intent.putExtra(LANDING_PAGE_USERNAME, userName);
        intent.putExtra("adminCheck", admin);
        intent.putExtra("user_id", userId);
        return intent;
    }
}
