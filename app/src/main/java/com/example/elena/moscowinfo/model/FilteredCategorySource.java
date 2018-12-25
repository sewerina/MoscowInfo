package com.example.elena.moscowinfo.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import androidx.annotation.NonNull;

public class FilteredCategorySource implements CategorySource {

    private List<Category> mCategories = new ArrayList<>();

    public FilteredCategorySource(CategorySource source, String query) {
        String searchText = query.toLowerCase();
        for (Category category : source) {
            if (category.text().toLowerCase().contains(searchText)) {
                mCategories.add(category);
            }
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

    @Override
    public void update() {

    }

    @NonNull
    @Override
    public Iterator<Category> iterator() {
        return mCategories.iterator();
    }
}
