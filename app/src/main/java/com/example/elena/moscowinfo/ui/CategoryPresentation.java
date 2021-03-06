package com.example.elena.moscowinfo.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.elena.moscowinfo.R;
import com.example.elena.moscowinfo.model.Category;
import com.squareup.picasso.Picasso;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryPresentation {
    private View mView;
    private ViewGroup mViewGroup;

    @BindView(R.id.iv_categoryImage)
    ImageView mImageView;

    @BindView(R.id.tv_category)
    TextView mTextView;

    private Category mCategory;

    public CategoryPresentation(ViewGroup viewGroup) {
        mViewGroup = viewGroup;
    }

    public View view() {
        if (mView == null) {
            mView = LayoutInflater.from(mViewGroup.getContext()).inflate(R.layout.category_item, mViewGroup, false);
        }
        ButterKnife.bind(this, mView);
        return mView;
    }

    public void bind(Category category) {
        mTextView.setText(category.text());
        Picasso.get().load(category.image()).into(mImageView);
        mCategory = category;
    }

    public Intent navigateToCategoryActivity() {
        Intent intent = null;
        if (mCategory != null) {
            intent = CategoryActivity.newIntent(mViewGroup.getContext(), mCategory.text(), mCategory.id());
        }
        // TODO: do not return null
        return intent;
    }

}
