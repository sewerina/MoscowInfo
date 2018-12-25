package com.example.elena.moscowinfo.model;

public interface CategorySource extends Iterable<Category> {
//    List<Category> categories();
    int size();
    Category onPosition(int position);

    void update();
}
