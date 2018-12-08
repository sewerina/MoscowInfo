package com.example.elena.moscowinfo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.elena.moscowinfo.MoscowInfoApp;
import com.example.elena.moscowinfo.R;
import com.example.elena.moscowinfo.model.CategoryDetails;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryFragment extends Fragment {

    @BindView(R.id.tv_departmentCaption)
    TextView mDepartmentCaptionTv;

    @BindView(R.id.tv_description)
    TextView mDescriptionTv;

    @BindView(R.id.tv_fullDescription)
    TextView mFullDescriptionTv;

    @BindView(R.id.btn_moreDetails)
    Button mMoreDetailsBtn;

    private CategoryViewModel mCategoryViewModel;


    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCategoryViewModel = ViewModelProviders.of(this, MoscowInfoApp.factory()).get(CategoryViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, v);

        mCategoryViewModel.mCategoryDetails.observe(this, new Observer<CategoryDetails>() {
            @Override
            public void onChanged(CategoryDetails categoryDetails) {
                if (categoryDetails != null) {
                    mDepartmentCaptionTv.setText(categoryDetails.department());
                    mDepartmentCaptionTv.setText(categoryDetails.description());
                    mFullDescriptionTv.setText(categoryDetails.fullDescription());
                }

            }
        });

        return v;
    }
}
