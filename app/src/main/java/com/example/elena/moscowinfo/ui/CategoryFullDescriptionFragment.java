package com.example.elena.moscowinfo.ui;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class CategoryFullDescriptionFragment extends Fragment {
    public static String TAG = "CategoryFullDescriptionFragment";

    @BindView(R.id.tv_fullDescription)
    TextView mFullDescriptionTv;

    private CategoryViewModel mCategoryViewModel;

    public static CategoryFullDescriptionFragment newInstance() {
        CategoryFullDescriptionFragment fragment = new CategoryFullDescriptionFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCategoryViewModel = ViewModelProviders.of(getActivity(), MoscowInfoApp.factory()).get(CategoryViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category_full_description, container, false);
        ButterKnife.bind(this, v);

        mCategoryViewModel.mCategoryDetails.observe(this, new Observer<CategoryDetails>() {
            @Override
            public void onChanged(CategoryDetails categoryDetails) {
                if (categoryDetails != null) {
                    String html = categoryDetails.fullDescription();
                    Spanned text;
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        text = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
                    } else {
                        text = Html.fromHtml(html);
                    }
                    mFullDescriptionTv.setText(text);
                }
            }
        });

        return v;
    }


}
