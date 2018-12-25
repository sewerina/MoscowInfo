package com.example.elena.moscowinfo.database;

import java.util.List;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface FavouriteDao {

    @Query("SELECT * from favourite")
    List<FavouriteEntity> getAllFavourites();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavourite(FavouriteEntity favouriteEntity);

    @Query("SELECT category.id as id, category.caption as caption, category.imageUrl as imageUrl from category INNER JOIN favourite on favourite.category_id = category.id")
    List<CategoryEntity> getFavouriteCategories();

    @Query("DELETE from favourite WHERE category_id = :categoryId")
    void deleteCategory(int categoryId);

    @Query("SELECT COUNT(*) from favourite WHERE category_id = :categoryId")
    int countByCategoryId(int categoryId);
}
