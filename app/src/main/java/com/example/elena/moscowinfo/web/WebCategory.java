package com.example.elena.moscowinfo.web;

import com.example.elena.moscowinfo.model.Category;
import java.util.Locale;

public class WebCategory implements Category {
    private Dataset mDataset;

    public WebCategory(Dataset dataset) {
        mDataset = dataset;
    }

    @Override
    public String text() {
        return mDataset.Caption;
    }

    @Override
    public String image() {
        String value = String.format(Locale.ROOT, "https://apidata.mos.ru/v1/datasets/%d/image?api_key=57e3d14c8a573455a02dae758bb975dc", mDataset.Id);
        return value;
    }

    @Override
    public int id() {
        return mDataset.Id;
    }
}
