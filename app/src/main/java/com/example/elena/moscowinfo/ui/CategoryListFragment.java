package com.example.elena.moscowinfo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.elena.moscowinfo.R;
import com.example.elena.moscowinfo.model.Category;
import com.example.elena.moscowinfo.model.CategorySource;
import com.example.elena.moscowinfo.model.FakeCategorySource;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryListFragment extends Fragment {

    @BindView(R.id.recyclerView_categories)
    RecyclerView mRecyclerView;

    private CategoryAdapter mCategoryAdapter;

    public static CategoryListFragment newInstance() {
        return new CategoryListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);

        ButterKnife.bind(this, view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        mCategoryAdapter = new CategoryAdapter(new FakeCategorySource());
        mRecyclerView.setAdapter(mCategoryAdapter);

        return view;
    }

    private class CategoryHolder extends RecyclerView.ViewHolder {

        @NonNull
        private final CategoryView mCategoryView;

        public CategoryHolder(@NonNull CategoryView categoryView) {
            super(categoryView.view());
            mCategoryView = categoryView;
        }


        public void bind(Category category) {
            mCategoryView.bind(category);
        }
    }

    private class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> {
        private final CategorySource mSource;

        public CategoryAdapter(CategorySource source) {
            mSource = source;
        }

        @NonNull
        @Override
        public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CategoryHolder(new CategoryView(getActivity()));
        }

        @Override
        public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
            Category category = mSource.onPosition(position);
            holder.bind(category);
        }

        @Override
        public int getItemCount() {
            return mSource.size();
        }
    }


}
