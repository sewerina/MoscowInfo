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
import androidx.lifecycle.ViewModelProviders;

public class CategoryActivity extends AppCompatActivity {
    private static final String EXTRA_CATEGORY_NAME = "com.example.elena.moscowinfo.ui.categoryName";
    private static final String EXTRA_CATEGORY_ID = "com.example.elena.moscowinfo.ui.categoryId";

    public static Intent newIntent(Context context, String categoryName, int categoryId) {
        Intent intent = new Intent(context, CategoryActivity.class);
        intent.putExtra(EXTRA_CATEGORY_NAME, categoryName);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

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

        String id = getIntent().getStringExtra(EXTRA_CATEGORY_ID);
        CategoryViewModel categoryViewModel = ViewModelProviders.of(this, MoscowInfoApp.factory()).get(CategoryViewModel.class);
        categoryViewModel.load(Integer.parseInt(id));
    }


}
