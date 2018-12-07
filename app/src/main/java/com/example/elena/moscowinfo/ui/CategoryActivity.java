package com.example.elena.moscowinfo.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.example.elena.moscowinfo.R;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CategoryActivity extends AppCompatActivity {
    private static final String EXTRA_CATEGORY_NAME = "com.example.elena.moscowinfo.ui.categoryName";

    public static Intent newIntent(Context context, String categoryName) {
        Intent intent = new Intent(context, CategoryActivity.class);
        intent.putExtra(EXTRA_CATEGORY_NAME, categoryName);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        String name = getIntent().getStringExtra(EXTRA_CATEGORY_NAME);
        setTitle(name);
    }
}
