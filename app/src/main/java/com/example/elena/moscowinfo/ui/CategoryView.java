package com.example.elena.moscowinfo.ui;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.example.elena.moscowinfo.model.Category;

public class CategoryView {
    private final Context mContext;
    private TextView mTextView;

    public CategoryView(Context context) {
        mContext = context;
    }

    public View view() {
        if (mTextView == null) {
            mTextView = new TextView(mContext);
        }
        return mTextView;
    }

    public void bind(Category category) {
        mTextView.setText(category.text());
    }

}
