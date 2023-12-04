package com.example.vision.DB;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.vision.Goal;
import com.example.vision.ProgressUpdate;
import com.example.vision.Quote;
import com.example.vision.Reminder;
import com.example.vision.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Database(entities = {User.class, Goal.class, Quote.class, ProgressUpdate.class, Reminder.class}, version = 5, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "Vision.db";
    public static final String USER_TABLE = "USER_TABLE";
    public static final String GOAL_TABLE = "GOAL_TABLE";
    public static final String QUOTE_TABLE = "QUOTE_TABLE";
    public static final String PROG_TABLE = "PROG_TABLE";
    public static final String REMINDER_TABLE = "REMINDER_TABLE";

    public abstract UserDao userDao();
    public abstract GoalDao goalDao();
    public abstract QuoteDao quoteDao();
    public abstract ProgressUpdateDao progressUpdateDao();
    public abstract ReminderDao reminderDao();

    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "user_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
