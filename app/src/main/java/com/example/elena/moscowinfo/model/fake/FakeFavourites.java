package com.example.elena.moscowinfo.model.fake;

import com.example.elena.moscowinfo.model.Category;
import com.example.elena.moscowinfo.model.Favourites;

import java.util.List;

public class FakeFavourites implements Favourites {

    @Override
    public void addToFavourites(int categoryId) {

    }

    @Override
    public void deleteFromFavourites(int categoryId) {

    }

    @Override
    public boolean isFavourite(int categoryId) {
        return false;
    }
}
