package com.example.vision;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.vision.DB.AppDatabase;

@Entity(tableName = AppDatabase.PROG_TABLE)
public class ProgressUpdate {
    @PrimaryKey(autoGenerate = true)
    private int mUpdateId;
    @ColumnInfo(name = "goal_id")
    private int mGoalId;
    private String mUpdateDate;
    private String mProgressInfo;

    public ProgressUpdate(int goalId, String updateDate, String progressInfo) {
        mGoalId = goalId;
        mUpdateDate = updateDate;
        mProgressInfo = progressInfo;
    }

    public int getUpdateId() {
        return mUpdateId;
    }

    public void setUpdateId(int updateId) {
        mUpdateId = updateId;
    }

    public int getGoalId() {
        return mGoalId;
    }

    public void setGoalId(int goalId) {
        mGoalId = goalId;
    }

    public String getUpdateDate() {
        return mUpdateDate;
    }

    public void setUpdateDate(String updateDate) {
        mUpdateDate = updateDate;
    }

    public String getProgressInfo() {
        return mProgressInfo;
    }

    public void setProgressInfo(String progressInfo) {
        mProgressInfo = progressInfo;
    }
}
