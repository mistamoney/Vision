package com.example.vision;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.vision.DB.AppDatabase;

@Entity(tableName = AppDatabase.GOAL_TABLE)
public class Goal {
    @PrimaryKey(autoGenerate = true)
    private int mGoalId;
    private String mTitle;
    private String mDescription;
    private String mDeadline;
    private String mCategory;

    public Goal(String title, String description, String deadline, String category) {
        this.mTitle = title;
        this.mDescription = description;
        this.mDeadline = deadline;
        this.mCategory = category;
    }

    public int getGoalId() {
        return mGoalId;
    }
    public void setGoalId(int goalId) {
        this.mGoalId = goalId;
    }
    public String getTitle() {
        return mTitle;
    }
    public void setTitle(String title) {
        this.mTitle = title;
    }
    public String getDescription() {
        return mDescription;
    }
    public void setDescription(String description) {
        this.mDescription = description;
    }
    public String getDeadline() {
        return mDeadline;
    }
    public void setDeadline(String deadline) {
        this.mDeadline = deadline;
    }
    public String getCategory() {
        return mCategory;
    }
    public void setCategory(String category) {
        this.mCategory = category;
    }
}
