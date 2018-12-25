package com.example.elena.moscowinfo.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "favourite")
public class FavouriteEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int mId;

    @ColumnInfo(name = "category_id")
    @ForeignKey(entity = CategoryEntity.class, parentColumns = "id", childColumns = "category_id")
    public int mCategoryId;

}
