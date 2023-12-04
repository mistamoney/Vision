package com.example.vision;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.vision.DB.AppDatabase;

@Entity(tableName = AppDatabase.QUOTE_TABLE)
public class Quote {
    @PrimaryKey(autoGenerate = true)
    private int mQuoteId;
    private String mText;
    private String mAuthor;

    public Quote(String text, String author) {
        this.mText = text;
        this.mAuthor = author;
    }

    public int getQuoteId() {
        return mQuoteId;
    }

    public void setQuoteId(int quoteID) {
        this.mQuoteId = quoteID;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText = text;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        this.mAuthor = author;
    }
}