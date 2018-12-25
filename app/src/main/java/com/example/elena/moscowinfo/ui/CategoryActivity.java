package com.example.elena.moscowinfo.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.example.elena.moscowinfo.MoscowInfoApp;
import com.example.elena.moscowinfo.R;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity {
    private static final String EXTRA_CATEGORY_NAME = "com.example.elena.moscowinfo.ui.categoryName";
    private static final String EXTRA_CATEGORY_ID = "com.example.elena.moscowinfo.ui.categoryId";

    @BindView(R.id.refresher_category)
    SwipeRefreshLayout mRefreshLayout;

    public static Intent newIntent(Context context, String categoryName, int categoryId) {
        Intent intent = new Intent(context, CategoryActivity.class);
        intent.putExtra(EXTRA_CATEGORY_NAME, categoryName);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        ButterKnife.bind(this);

        String name = getIntent().getStringExtra(EXTRA_CATEGORY_NAME);
        setTitle(name);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = CategoryFragment.newInstance();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

        final int id = getIntent().getIntExtra(EXTRA_CATEGORY_ID, -3);
        final CategoryViewModel categoryViewModel = ViewModelProviders.of(this, MoscowInfoApp.factory()).get(CategoryViewModel.class);
        categoryViewModel.mIsRefresh.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean value) {
                mRefreshLayout.setRefreshing(value);
            }
        });

        if (id > 0) {
            categoryViewModel.load(id);
        }

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                categoryViewModel.load(id);
            }
        });

    }


}
