package com.example.elena.moscowinfo;

import android.app.Application;

import com.example.elena.moscowinfo.model.DatabaseCategorySource;
import com.example.elena.moscowinfo.ui.CategoryListViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MoscowInfoApp extends Application implements ViewModelProvider.Factory {
    private static ViewModelProvider.Factory sFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        sFactory = this;
    }

    public static ViewModelProvider.Factory factory() {
        return sFactory;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == CategoryListViewModel.class) {
            return (T) new CategoryListViewModel(new DatabaseCategorySource(this, "moscow.db"));
        }
        return null;
    }
}
