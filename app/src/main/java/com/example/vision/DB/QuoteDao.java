package com.example.vision.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;

import com.example.vision.Quote;

import java.util.List;

@Dao
public interface QuoteDao {
    @Insert
    void insert(Quote quote);

    @Delete
    void delete(Quote quote);

    @Query("SELECT * FROM " + AppDatabase.QUOTE_TABLE)
    LiveData<List<Quote>> getAllQuotes();

    @Query("SELECT * FROM " + AppDatabase.QUOTE_TABLE + " WHERE mQuoteId = :quoteId")
    LiveData<Quote> getQuoteById(int quoteId);

}
