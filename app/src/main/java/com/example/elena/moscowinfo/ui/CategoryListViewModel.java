package com.example.elena.moscowinfo.ui;

import com.example.elena.moscowinfo.database.FavouriteCategorySource;
import com.example.elena.moscowinfo.model.CategorySource;
import com.example.elena.moscowinfo.model.CategorySources;
import com.example.elena.moscowinfo.database.DatabaseCategorySource;
import com.example.elena.moscowinfo.model.FilteredCategorySource;
import com.example.elena.moscowinfo.model.fake.FakeCategorySource;
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

public class CategoryListViewModel extends ViewModel {
    public final MutableLiveData<CategorySource> mCategorySource = new MutableLiveData<>();

    private final Action mHideRefresh = new Action() {
        @Override
        public void run() throws Exception {
            mIsRefresh.postValue(false);
        }
    };
    private final Consumer<Disposable> mShowRefresh  = new Consumer<Disposable>() {
        @Override
        public void accept(Disposable disposable) throws Exception {
            mIsRefresh.postValue(true);
        }
    };
    public MutableLiveData<Boolean> mIsRefresh = new MutableLiveData<>();
    private CompositeDisposable mDisposable = new CompositeDisposable();

    private CategorySources mCategorySources;


    public CategoryListViewModel(final CategorySources categorySources) {
        mCategorySources = categorySources;
        Disposable subscribe = Single.fromCallable(new Callable<CategorySource>() {
                    @Override
                    public CategorySource call() throws Exception {
                        DatabaseCategorySource databaseCategorySource = mCategorySources.databaseCategorySource();
                        if (databaseCategorySource.size() == 0) {
                            WebCategorySource webCategorySource = mCategorySources.webCategorySource();
                            databaseCategorySource.addCategories(webCategorySource);
                        }

                        return databaseCategorySource;
//                        return mCategorySources.webCategorySource();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(mShowRefresh)
                .doFinally(mHideRefresh)
                .subscribe(new Consumer<CategorySource>() {
                    @Override
                    public void accept(CategorySource categorySource) throws Exception {
                        mCategorySource.setValue(categorySource);
                    }
                });

        mDisposable.add(subscribe);
    }

    public void loading() {
        Disposable subscribe = Single.fromCallable(new Callable<CategorySource>() {
                    @Override
                    public CategorySource call() throws Exception {
                        WebCategorySource webCategorySource = mCategorySources.webCategorySource();
                        return webCategorySource;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(mShowRefresh)
                .doFinally(mHideRefresh)
                .doOnSuccess(new Consumer<CategorySource>() {
                    @Override
                    public void accept(CategorySource source) throws Exception {
                        DatabaseCategorySource databaseCategorySource = mCategorySources.databaseCategorySource();
                        databaseCategorySource.addCategories(source);
                    }
                })
                .subscribe(new Consumer<CategorySource>() {
                    @Override
                    public void accept(CategorySource categorySource) throws Exception {
                        mCategorySource.setValue(categorySource);
                    }
                });
        mDisposable.add(subscribe);
    }

    public void showFavourites() {
        mCategorySource.postValue(mCategorySources.favouriteCategorySource());
    }

    public void showAll(){
        mCategorySource.postValue(mCategorySources.databaseCategorySource());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mDisposable.clear();
    }

    public void searchByQuery(String query) {
        CategorySource currentSource = mCategorySource.getValue();
        mCategorySource.setValue(new FilteredCategorySource(currentSource, query));
    }

    public void updateCategorySource() {
        CategorySource value = mCategorySource.getValue();
        if (value != null) {
            value.update();
            mCategorySource.postValue(value);
        }
    }
}
