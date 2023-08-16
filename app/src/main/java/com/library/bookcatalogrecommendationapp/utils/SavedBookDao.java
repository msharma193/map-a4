package com.library.bookcatalogrecommendationapp.utils;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.library.bookcatalogrecommendationapp.models.SavedBook;

import java.util.List;

@Dao
public interface SavedBookDao {
    @Insert
    void insert(SavedBook savedBook);

    @Query("SELECT * FROM saved_books")
    LiveData<List<SavedBook>> getAllSavedBooks();

    @Update
    void updateSavedBook(SavedBook savedBook);

    @Delete
    void deleteSavedBook(SavedBook savedBook);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrReplaceSavedBook(SavedBook savedBook);

    @Query("SELECT * FROM saved_books WHERE isRead = 1")
    List<SavedBook> getReadSavedBooks();

    @Query("SELECT * FROM saved_books WHERE isRead = 0")
    List<SavedBook> getIntrestedInBook();
}
