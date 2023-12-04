package com.example.vision.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import com.example.vision.Goal;

import java.util.List;
@Dao
public interface GoalDao {
    @Insert
    void insert(Goal goal);

    @Update
    void update(Goal goal);

    @Delete
    void delete(Goal goal);

    @Query("SELECT * FROM " + AppDatabase.GOAL_TABLE)
    LiveData<List<Goal>> getAllGoals();

    @Query("SELECT * FROM " + AppDatabase.GOAL_TABLE + " WHERE mGoalId = :goalId")
    Goal getGoalById(int goalId);
}
