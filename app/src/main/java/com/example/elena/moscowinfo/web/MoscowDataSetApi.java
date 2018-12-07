package com.example.elena.moscowinfo.web;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoscowDataSetApi {
    @GET("v1/datasets")
    Call<List<Dataset>> datasets(@Query("api_key") String apiKey);

    @GET("v1/datasets/{id}")
    Call<DatasetDetails> details(@Path("id") int id, @Query("api_key") String apiKey);

}
