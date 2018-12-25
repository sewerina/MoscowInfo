package com.example.elena.moscowinfo.model;

import android.content.Context;

import com.example.elena.moscowinfo.database.AppDatabase;
import com.example.elena.moscowinfo.database.DatabaseCategorySource;
import com.example.elena.moscowinfo.database.FavouriteCategorySource;
import com.example.elena.moscowinfo.web.WebCategorySource;

import androidx.room.Room;

public class CategorySources {


    private AppDatabase mAppDatabase;

    public CategorySources(AppDatabase appDatabase) {

        mAppDatabase = appDatabase;
    }

    public WebCategorySource webCategorySource() {
        return new WebCategorySource();
    }

    public DatabaseCategorySource databaseCategorySource() {
        return new DatabaseCategorySource(mAppDatabase);
    }

    public FavouriteCategorySource favouriteCategorySource() {
        return new FavouriteCategorySource(mAppDatabase);
    }


}
