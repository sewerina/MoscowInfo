package com.example.elena.moscowinfo.ui;

import android.os.Handler;
import android.os.Message;

import com.example.elena.moscowinfo.database.DatabaseCategorySource;
import com.example.elena.moscowinfo.model.CategoryDetails;
import com.example.elena.moscowinfo.model.CategorySource;
import com.example.elena.moscowinfo.model.Favourites;
import com.example.elena.moscowinfo.model.fake.FakeCategoryDetails;
import com.example.elena.moscowinfo.web.WebCategoryDetails;
import com.example.elena.moscowinfo.web.WebCategorySource;

import java.util.concurrent.Callable;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CategoryViewModel extends ViewModel {

    public final MutableLiveData<CategoryDetails> mCategoryDetails = new MutableLiveData<>();
    public MutableLiveData<Boolean> mIsRefresh = new MutableLiveData<>();
    private final Action mHideRefresh = new Action() {
        @Override
        public void run() throws Exception {
            mIsRefresh.postValue(false);
        }
    };
    private final Consumer<Disposable> mShowRefresh = new Consumer<Disposable>() {
        @Override
        public void accept(Disposable disposable) throws Exception {
            mIsRefresh.postValue(true);
        }
    };

    private int mCategoryId;

    private Favourites mFavourites;

    private CompositeDisposable mDisposable = new CompositeDisposable();

    public CategoryViewModel(Favourites favourites) {
        mFavourites = favourites;
    }

    public void load(final int categoryId) {
        mCategoryId = categoryId;
        Disposable subscribe = Single.fromCallable(new Callable<CategoryDetails>() {
            @Override
            public CategoryDetails call() throws Exception {
                WebCategoryDetails webCategoryDetails = new WebCategoryDetails(categoryId);
                return webCategoryDetails;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(mShowRefresh)
                .doFinally(mHideRefresh)
                .subscribe(new Consumer<CategoryDetails>() {
                    @Override
                    public void accept(CategoryDetails categoryDetails) throws Exception {
                        mCategoryDetails.setValue(categoryDetails);
                    }
                });
        mDisposable.add(subscribe);

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposable.clear();
    }

    public void favourites(boolean isFavourite) {
        if (isFavourite) {
            mFavourites.addToFavourites(mCategoryId);
        } else {
            mFavourites.deleteFromFavourites(mCategoryId);
        }
    }

    public boolean isFavouriteCategory() {
        return mFavourites.isFavourite(mCategoryId);
    }
}
