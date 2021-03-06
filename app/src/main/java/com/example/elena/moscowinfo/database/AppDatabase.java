package com.example.elena.moscowinfo.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CategoryEntity.class, FavouriteEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CategoryDao categoryDao();

    public abstract FavouriteDao favouriteDao();
}
