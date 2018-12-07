package com.example.elena.moscowinfo.model.fake;

import com.example.elena.moscowinfo.model.Category;

public class FakeCategory implements Category {
    private final String mValue;

    public FakeCategory(String value) {
        mValue = value;
    }

    public FakeCategory() {
        mValue = "Fake Category";
    }

    @Override
    public String text() {
        return mValue;
    }

    @Override
    public String image() {
        return null;
    }
}
