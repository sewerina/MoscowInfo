package com.example.elena.moscowinfo.model;

import java.util.ArrayList;

public class FakeCategorySource implements CategorySource {

    private final ArrayList<Category> mCategories = new ArrayList<>();

    public FakeCategorySource() {
        for (int i = 0; i < 10; i++) {
            mCategories.add(new FakeCategory());
        }
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
