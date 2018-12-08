package com.example.elena.moscowinfo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.elena.moscowinfo.MoscowInfoApp;
import com.example.elena.moscowinfo.R;
import com.example.elena.moscowinfo.model.Category;
import com.example.elena.moscowinfo.model.CategorySource;
import com.example.elena.moscowinfo.model.fake.FakeCategorySource;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryListFragment extends Fragment {

    @BindView(R.id.recyclerView_categories)
    RecyclerView mRecyclerView;

    @BindView(R.id.refresher_categories)
    SwipeRefreshLayout mRefreshLayout;

    private CategoryAdapter mCategoryAdapter;

    private CategoryListViewModel mCategoryListViewModel;

    public static CategoryListFragment newInstance() {
        return new CategoryListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCategoryListViewModel = ViewModelProviders.of(this, MoscowInfoApp.factory()).get(CategoryListViewModel.class);

        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_category_list, menu);

        MenuItem searchItem = menu.findItem(R.id.bar_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mCategoryListViewModel.searchByQuery(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bar_search:
//                Toast.makeText(getContext(), "Add filter", Toast.LENGTH_SHORT).show();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_list, container, false);

        ButterKnife.bind(this, view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mCategoryAdapter = new CategoryAdapter(new FakeCategorySource(0));
        mRecyclerView.setAdapter(mCategoryAdapter);

        mCategoryListViewModel.mCategorySource.observe(this, mCategoryAdapter);

        mCategoryListViewModel.mIsRefresh.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean value) {
                mRefreshLayout.setRefreshing(value);
            }
        });

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCategoryListViewModel.loading();
            }
        });

        return view;
    }

    private class CategoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @NonNull
        private final CategoryPresentation mCategoryPresentation;

        public CategoryHolder(@NonNull CategoryPresentation categoryPresentation) {
            super(categoryPresentation.view());
            this.itemView.setOnClickListener(this);
            mCategoryPresentation = categoryPresentation;
        }

        public void bind(Category category) {
            mCategoryPresentation.bind(category);
        }

        @Override
        public void onClick(View v) {
            Intent intent = mCategoryPresentation.navigateToCategoryActivity();
            startActivity(intent);
        }
    }

    private class CategoryAdapter extends RecyclerView.Adapter<CategoryHolder> implements Observer<CategorySource> {
        private CategorySource mSource;

        public CategoryAdapter(@NonNull CategorySource source) {
            mSource = source;
        }

        @NonNull
        @Override
        public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CategoryHolder(new CategoryPresentation(parent));
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


        @Override
        public void onChanged(CategorySource source) {
            mSource = source;
            notifyDataSetChanged();
        }
    }


}
