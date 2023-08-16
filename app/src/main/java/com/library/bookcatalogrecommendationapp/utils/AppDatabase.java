package com.library.bookcatalogrecommendationapp.utils;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.library.bookcatalogrecommendationapp.models.SavedBook;

@Database(entities = {SavedBook.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SavedBookDao savedBookDao();

}
