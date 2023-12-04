package com.example.vision.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vision.Reminder;

import java.util.List;

@Dao
public interface ReminderDao {
    @Insert
    void insert(Reminder reminder);

    @Update
    void update(Reminder reminder);

    @Delete
    void delete(Reminder reminder);

    @Query("SELECT * FROM " + AppDatabase.REMINDER_TABLE + " WHERE goal_id = :goalId")
    LiveData<List<Reminder>> getRemindersForGoal(int goalId);
}
