package com.example.elena.moscowinfo.model.fake;

import com.example.elena.moscowinfo.model.CategoryDetails;

public class FakeCategoryDetails implements CategoryDetails {
    @Override
    public String department() {
        return "Fake department";
    }

    @Override
    public String description() {
        return "Fake description";
    }

    @Override
    public String fullDescription() {
        return "Full Description";
    }
}
