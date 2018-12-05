package com.example.elena.moscowinfo.model;

import com.example.elena.moscowinfo.database.CategoryEntity;

public class DatabaseCategory implements Category {
    private CategoryEntity mCategoryEntity;

    public DatabaseCategory(CategoryEntity categoryEntity) {
        mCategoryEntity = categoryEntity;
    }

    @Override
    public String text() {
        return mCategoryEntity.mCaption;
    }
}
