package com.example.elena.moscowinfo;

import android.app.Application;

import com.example.elena.moscowinfo.database.AppDatabase;
import com.example.elena.moscowinfo.database.FavouriteCategories;
import com.example.elena.moscowinfo.model.CategorySources;
import com.example.elena.moscowinfo.model.fake.FakeFavourites;
import com.example.elena.moscowinfo.ui.CategoryListViewModel;
import com.example.elena.moscowinfo.ui.CategoryViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

public class MoscowInfoApp extends Application implements ViewModelProvider.Factory {
    private static ViewModelProvider.Factory sFactory;
    private AppDatabase mAppDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        sFactory = this;

        mAppDatabase = Room.databaseBuilder(this,
                AppDatabase.class, "moscow.db")
                .enableMultiInstanceInvalidation()
                .allowMainThreadQueries()
                .build();
    }

    public static ViewModelProvider.Factory factory() {
        return sFactory;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == CategoryListViewModel.class) {
            return (T) new CategoryListViewModel(new CategorySources(mAppDatabase));
        } else {
            return (T) new CategoryViewModel(new FavouriteCategories(mAppDatabase.favouriteDao()));
        }
    }
}
