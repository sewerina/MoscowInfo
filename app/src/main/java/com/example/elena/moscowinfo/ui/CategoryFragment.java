package com.example.elena.moscowinfo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import com.example.elena.moscowinfo.MoscowInfoApp;
import com.example.elena.moscowinfo.R;
import com.example.elena.moscowinfo.model.CategoryDetails;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryFragment extends Fragment {
    public static String TAG = "CategoryFragment";

    @BindView(R.id.tv_departmentCaption)
    TextView mDepartmentCaptionTv;

    @BindView(R.id.tv_description)
    TextView mDescriptionTv;

    @BindView(R.id.btn_moreDetails)
    Button mMoreDetailsBtn;

    @BindView(R.id.switch_favourites)
    Switch mSwitchFavourites;

    private CategoryViewModel mCategoryViewModel;

    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();

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
        View v = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, v);

        mCategoryViewModel.mCategoryDetails.observe(this, new Observer<CategoryDetails>() {
            @Override
            public void onChanged(CategoryDetails categoryDetails) {
                if (categoryDetails != null) {
                    mDepartmentCaptionTv.setText(categoryDetails.department());
                    mDescriptionTv.setText(categoryDetails.description());
                }

            }
        });

        View.OnClickListener listenerOnClickMoreDetails = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    Fragment fragment = fragmentManager.findFragmentByTag(CategoryFullDescriptionFragment.TAG);
                    if (fragment == null) {
                        fragment = CategoryFullDescriptionFragment.newInstance();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment)
                                .addToBackStack(CategoryFullDescriptionFragment.TAG)
                                .commit();
                    }
                }
            }
        };
        mMoreDetailsBtn.setOnClickListener(listenerOnClickMoreDetails);

        if (mCategoryViewModel.isFavouriteCategory()) {
            mSwitchFavourites.setChecked(true);
        }

        CompoundButton.OnCheckedChangeListener listenerOnChangeFavourites = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCategoryViewModel.favourites(isChecked);
            }
        };
        mSwitchFavourites.setOnCheckedChangeListener(listenerOnChangeFavourites);

        return v;
    }


}
