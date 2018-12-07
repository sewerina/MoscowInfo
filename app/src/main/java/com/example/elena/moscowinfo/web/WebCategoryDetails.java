package com.example.elena.moscowinfo.web;

import com.example.elena.moscowinfo.model.CategoryDetails;
import java.io.IOException;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebCategoryDetails implements CategoryDetails {

    private DatasetDetails mDetails;

    public WebCategoryDetails(int categoryId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apidata.mos.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MoscowDataSetApi dataSetApi = retrofit.create(MoscowDataSetApi.class);
        try {
            mDetails = dataSetApi.details(categoryId, "57e3d14c8a573455a02dae758bb975dc").execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String department() {
        return mDetails.DepartmentCaption;
    }

    @Override
    public String description() {
        return mDetails.Description;
    }

    @Override
    public String fullDescription() {
        return mDetails.FullDescription;
    }
}
