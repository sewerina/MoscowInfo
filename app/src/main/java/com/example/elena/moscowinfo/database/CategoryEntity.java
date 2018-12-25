package com.example.elena.moscowinfo.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category")
public class CategoryEntity {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    public int mId;


    @ColumnInfo(name = "caption")
    public String mCaption;

    @ColumnInfo(name = "imageUrl")
    public String mImageUrl;

}
