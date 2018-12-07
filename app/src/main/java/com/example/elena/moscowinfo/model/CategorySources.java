package com.example.elena.moscowinfo.model;

import android.content.Context;

import com.example.elena.moscowinfo.database.DatabaseCategorySource;
import com.example.elena.moscowinfo.web.WebCategorySource;

public class CategorySources {
    private Context mContext;

    public CategorySources(Context context) {
        mContext = context;
    }

    public WebCategorySource webCategorySource() {
        return new WebCategorySource();
    }

    public DatabaseCategorySource databaseCategorySource() {
        return new DatabaseCategorySource(mContext, "moscow.db");
    }
}
