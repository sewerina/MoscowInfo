package com.example.elena.moscowinfo.database;

import com.example.elena.moscowinfo.model.Favourites;
import java.util.List;

public class FavouriteCategories implements Favourites {
    private FavouriteDao mFavouriteDao;

    public FavouriteCategories(FavouriteDao favouriteDao) {
        mFavouriteDao = favouriteDao;
    }

    @Override
    public void addToFavourites(int categoryId) {
        FavouriteEntity favouriteEntity = new FavouriteEntity();
        favouriteEntity.mCategoryId = categoryId;
        mFavouriteDao.insertFavourite(favouriteEntity);
    }

    @Override
    public void deleteFromFavourites(int categoryId) {
        mFavouriteDao.deleteCategory(categoryId);
    }

    @Override
    public boolean isFavourite(int categoryId) {
        return mFavouriteDao.countByCategoryId(categoryId) > 0;
    }
}
