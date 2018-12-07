package com.example.elena.moscowinfo.database;

import android.content.Context;

import com.example.elena.moscowinfo.model.Category;
import com.example.elena.moscowinfo.model.CategorySource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.Room;

public class DatabaseCategorySource implements CategorySource {
    private CategoryDao mCategoryDao;

    private List<CategoryEntity> mCategoryEntities;

    public DatabaseCategorySource(AppDatabase appDatabase) {
        mCategoryDao = appDatabase.categoryDao();
        mCategoryEntities = mCategoryDao.getAllCategories();
    }

    /**
     * Use for tests
     * @param context
     */
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

    public void addCategories(Iterable<Category> categories) {
        List<CategoryEntity> entities = new ArrayList<>();

        for (Category category : categories) {
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.mCaption = category.text();
            categoryEntity.mImageUrl = category.image();
            entities.add(categoryEntity);
        }

        mCategoryDao.insertAllCategories(entities);
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

    @NonNull
    @Override
    public Iterator<Category> iterator() {
        List<Category> categories = new ArrayList<>();
        for (CategoryEntity categoryEntity : mCategoryEntities) {
            categories.add(new DatabaseCategory(categoryEntity));
        }
        return categories.iterator();
    }
}
