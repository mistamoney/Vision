package com.example.vision.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vision.ProgressUpdate;

import java.util.List;

@Dao
public interface ProgressUpdateDao {
    @Insert
    void insert(ProgressUpdate progressUpdate);

    @Update
    void update(ProgressUpdate progressUpdate);

    @Delete
    void delete(ProgressUpdate progressUpdate);

    @Query("SELECT * FROM " + AppDatabase.PROG_TABLE + " WHERE goal_id = :goalId")
    LiveData<List<ProgressUpdate>> getUpdatesForGoal(int goalId);
}
