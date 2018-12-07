package com.example.elena.moscowinfo.database;

import java.util.List;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CategoryDao {
    @Query("SELECT * from category")
    List<CategoryEntity> getAllCategories();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllCategories(List<CategoryEntity> categoryEntities);
}
