package com.example.elena.moscowinfo.model.fake;

import com.example.elena.moscowinfo.model.Category;

public class FakeCategory implements Category {
    private final String mValue;

    private int mId;

    public FakeCategory(String value) {
        mValue = value;
    }

    public FakeCategory(int id) {
        mId = id;
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

    @Override
    public int id() {
        return mId;
    }
}
