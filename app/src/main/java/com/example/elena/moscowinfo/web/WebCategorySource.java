package com.example.elena.moscowinfo.web;

import com.example.elena.moscowinfo.model.Category;
import com.example.elena.moscowinfo.model.CategorySource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import androidx.annotation.NonNull;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebCategorySource implements CategorySource {
    private List<Dataset> mDatasets;
    private MoscowDataSetApi mDataSetApi;

    public WebCategorySource() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apidata.mos.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mDataSetApi = retrofit.create(MoscowDataSetApi.class);
        try {
            List<Dataset> AllDatasets = mDataSetApi.datasets("57e3d14c8a573455a02dae758bb975dc").execute().body();
            List<Dataset> noArchivDatasets = new ArrayList<>();
            for (Dataset dataset : AllDatasets) {
                if (!dataset.Caption.toLowerCase().startsWith("(архив)")) {
                    noArchivDatasets.add(dataset);
                }
            }
            mDatasets = noArchivDatasets;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        return mDatasets.size();
    }

    @Override
    public Category onPosition(int position) {
        return new WebCategory(mDatasets.get(position));
    }

    @Override
    public void update() {

    }

    @NonNull
    @Override
    public Iterator<Category> iterator() {
        List<Category> categories = new ArrayList<>();
        for (Dataset dataset : mDatasets) {
            categories.add(new WebCategory(dataset));
        }
        return categories.iterator();
    }
}
