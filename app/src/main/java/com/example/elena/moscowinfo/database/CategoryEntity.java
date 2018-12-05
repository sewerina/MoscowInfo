package com.example.elena.moscowinfo.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category")
public class CategoryEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int mId;


    @ColumnInfo(name = "caption")
    public String mCaption;
}
