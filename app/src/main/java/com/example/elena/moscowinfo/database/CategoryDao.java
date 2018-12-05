package com.example.elena.moscowinfo.database;

import java.util.List;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CategoryDao {
    @Query("SELECT * from category")
    List<CategoryEntity> getAllCategories();

    @Insert
    void insertAllCategories(List<CategoryEntity> categoryEntities);
}
