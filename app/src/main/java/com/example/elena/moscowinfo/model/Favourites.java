package com.example.elena.moscowinfo.model;

import java.util.List;

public interface Favourites {

    void addToFavourites(int categoryId);

    void deleteFromFavourites(int categoryId);

    boolean isFavourite(int categoryId);
}
