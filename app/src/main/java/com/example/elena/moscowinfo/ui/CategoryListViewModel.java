package com.example.elena.moscowinfo.ui;

import com.example.elena.moscowinfo.model.CategorySource;
import com.example.elena.moscowinfo.model.DatabaseCategorySource;
import com.example.elena.moscowinfo.model.FakeCategorySource;
import com.example.elena.moscowinfo.model.WebCategorySource;

import java.util.concurrent.Callable;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CategoryListViewModel extends ViewModel {
    public final MutableLiveData<CategorySource> mCategorySource = new MutableLiveData<>();


    public CategoryListViewModel(DatabaseCategorySource databaseCategorySource) {


        Disposable subscribe = Single.fromCallable(new Callable<CategorySource>() {
                    @Override
                    public CategorySource call() throws Exception {
                        return new WebCategorySource();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CategorySource>() {
                    @Override
                    public void accept(CategorySource webCategorySource) throws Exception {
                        mCategorySource.setValue(webCategorySource);
                    }
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
