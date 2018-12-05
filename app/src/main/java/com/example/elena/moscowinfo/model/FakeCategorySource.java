package com.example.elena.moscowinfo.model;

import java.util.ArrayList;

public class FakeCategorySource implements CategorySource {

    private final ArrayList<Category> mCategories = new ArrayList<>();

    public FakeCategorySource(int size) {
        for (int i = 0; i < size; i++) {
            mCategories.add(new FakeCategory());
        }
    }

    public FakeCategorySource() {
        this(10);
    }

    @Override
    public int size() {
        return mCategories.size();
    }

    @Override
    public Category onPosition(int position) {
        return mCategories.get(position);
    }
}
