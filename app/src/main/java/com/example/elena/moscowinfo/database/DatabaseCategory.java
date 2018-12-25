package com.example.elena.moscowinfo.database;

import com.example.elena.moscowinfo.model.Category;

public class DatabaseCategory implements Category {
    private CategoryEntity mCategoryEntity;

    public DatabaseCategory(CategoryEntity categoryEntity) {
        mCategoryEntity = categoryEntity;
    }

    @Override
    public String text() {
        return mCategoryEntity.mCaption;
    }

    @Override
    public String image() {
        return mCategoryEntity.mImageUrl;
    }

    @Override
    public int id() {
        return mCategoryEntity.mId;
    }

}
