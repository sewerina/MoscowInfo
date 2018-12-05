package com.example.elena.moscowinfo.model;

import android.content.Context;
import com.example.elena.moscowinfo.database.AppDatabase;
import com.example.elena.moscowinfo.database.CategoryDao;
import com.example.elena.moscowinfo.database.CategoryEntity;

import java.util.ArrayList;
import java.util.List;
import androidx.room.Room;

public class DatabaseCategorySource implements CategorySource {
    private CategoryDao mCategoryDao;

    private List<CategoryEntity> mCategoryEntities;

    public DatabaseCategorySource(Context context) {
        this(Room.inMemoryDatabaseBuilder(context,
                AppDatabase.class)
                .enableMultiInstanceInvalidation()
                .allowMainThreadQueries()
                .build());
    }

    public DatabaseCategorySource(Context context, String databaseName) {
        this(Room.databaseBuilder(context,
                AppDatabase.class, databaseName)
                .enableMultiInstanceInvalidation()
                .allowMainThreadQueries()
                .build());
    }

    public void addCategories(List<Category> categories) {
        List<CategoryEntity> entities = new ArrayList<>();

        for (Category category : categories) {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.mCaption = category.text();
            entities.add(categoryEntity);
        }


        mCategoryDao.insertAllCategories(entities);
        mCategoryEntities = mCategoryDao.getAllCategories();
    }

    public DatabaseCategorySource(AppDatabase appDatabase) {
        mCategoryDao = appDatabase.categoryDao();
        mCategoryEntities = mCategoryDao.getAllCategories();
    }

    @Override
    public int size() {
        return mCategoryEntities.size();
    }

    @Override
    public Category onPosition(int position) {
        return new DatabaseCategory(mCategoryEntities.get(position));
    }
}
