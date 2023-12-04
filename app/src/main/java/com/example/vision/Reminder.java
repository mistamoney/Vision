package com.example.vision;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.vision.DB.AppDatabase;

@Entity(tableName = AppDatabase.REMINDER_TABLE)
public class Reminder {
    @PrimaryKey(autoGenerate = true)
    private int reminderId;
    @ColumnInfo(name = "goal_id")
    private int goalId; // Foreign key from Goal table
    private String reminderTime;
    private String reminderNote;

    public Reminder(int reminderId, String reminderTime, String reminderNote) {
        this.reminderId = reminderId;
        this.reminderTime = reminderTime;
        this.reminderNote = reminderNote;
    }

    public int getReminderId() {
        return reminderId;
    }

    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }

    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public String getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(String reminderTime) {
        this.reminderTime = reminderTime;
    }

    public String getReminderNote() {
        return reminderNote;
    }

    public void setReminderNote(String reminderNote) {
        this.reminderNote = reminderNote;
    }
}