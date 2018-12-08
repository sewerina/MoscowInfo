package com.example.elena.moscowinfo.ui;

import com.example.elena.moscowinfo.model.CategoryDetails;
import com.example.elena.moscowinfo.web.WebCategoryDetails;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CategoryViewModel extends ViewModel {

    public final MutableLiveData<CategoryDetails> mCategoryDetails = new MutableLiveData<>();

    public CategoryViewModel() {

    }

    public void load(int categoryId) {
        mCategoryDetails.setValue(new WebCategoryDetails(categoryId));
    }
}
