package com.example.elena.moscowinfo.database;

import android.content.Context;
import com.example.elena.moscowinfo.model.Category;
import com.example.elena.moscowinfo.model.CategorySource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.room.Room;

public class FavouriteCategorySource implements CategorySource {
    private FavouriteDao mFavouriteDao;

    private List<CategoryEntity> mCategoryEntities;

    public FavouriteCategorySource(AppDatabase appDatabase) {
        mFavouriteDao = appDatabase.favouriteDao();
        mCategoryEntities = mFavouriteDao.getFavouriteCategories();
    }

    public FavouriteCategorySource(Context context) {
        this(Room.inMemoryDatabaseBuilder(context,
                AppDatabase.class)
                .enableMultiInstanceInvalidation()
                .allowMainThreadQueries()
                .build());
    }

    @Override
    public int size() {
        return mCategoryEntities.size();
    }

    @Override
    public Category onPosition(int position) {
        return new DatabaseCategory(mCategoryEntities.get(position));
    }

    @Override
    public void update() {
        mCategoryEntities = mFavouriteDao.getFavouriteCategories();
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
