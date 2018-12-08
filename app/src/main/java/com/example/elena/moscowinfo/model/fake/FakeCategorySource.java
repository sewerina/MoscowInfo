package com.example.elena.moscowinfo.model.fake;

import com.example.elena.moscowinfo.model.Category;
import com.example.elena.moscowinfo.model.CategorySource;
import java.util.ArrayList;
import java.util.Iterator;
import androidx.annotation.NonNull;

public class FakeCategorySource implements CategorySource {

    private final ArrayList<Category> mCategories = new ArrayList<>();

    public FakeCategorySource(int size) {
        for (int i = 0; i < size; i++) {
            mCategories.add(new FakeCategory(i));
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

    @NonNull
    @Override
    public Iterator<Category> iterator() {
        return mCategories.iterator();
    }
}
