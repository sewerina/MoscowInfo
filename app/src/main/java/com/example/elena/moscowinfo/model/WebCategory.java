package com.example.elena.moscowinfo.model;

import com.example.elena.moscowinfo.web.Dataset;

public class WebCategory implements Category {
    private Dataset mDataset;

    public WebCategory(Dataset dataset) {
        mDataset = dataset;
    }

    @Override
    public String text() {
        return mDataset.Caption;
    }
}
