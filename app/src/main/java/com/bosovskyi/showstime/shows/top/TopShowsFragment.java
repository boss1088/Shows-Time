package com.bosovskyi.showstime.shows.top;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bosovskyi.showstime.R;
import com.bosovskyi.showstime.data.source.entity.ShowShortEntity;
import com.bosovskyi.showstime.databinding.FragmentTopTvShowsBinding;
import com.bosovskyi.showstime.library.presentation.ui.fragment.BaseStateFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by boss1088 on 2/28/17.
 */

public class TopShowsFragment
        extends BaseStateFragment<FragmentTopTvShowsBinding, TopShowsContract.View, TopShowsState, TopShowsContract.Presenter>
        implements TopShowsContract.View {

    @Inject
    TopShowsPresenter presenter;

    @Inject
    Resources resources;

    private TopShowsAdapter topShowsAdapter;

    private Handler handler;

    public static TopShowsFragment newInstance() {
        return new TopShowsFragment();
    }

    @Override
    protected void callInjection() {
        getInjector().inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler = new Handler();

        topShowsAdapter = new TopShowsAdapter(state.topShows, this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final int columnCount;
        if (resources.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            columnCount = 3;
        } else {
            columnCount = 2;
        }

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), columnCount);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch(topShowsAdapter.getItemViewType(position)){
                    case TopShowsAdapter.VIEW_SHOW:
                        return 1;
                    case TopShowsAdapter.VIEW_LOADING:
                        return columnCount; //number of columns of the grid
                    default:
                        return -1;
                }
            }
        });
        binding.topShowsRecycler.setLayoutManager(layoutManager);
        binding.topShowsRecycler.setAdapter(topShowsAdapter);
        binding.topShowsRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                GridLayoutManager layoutManager = (GridLayoutManager) binding.topShowsRecycler.getLayoutManager();
                int itemCount = layoutManager.getItemCount();
                int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                if (lastVisiblePosition == (itemCount - 1)) {
                    handler.post(() -> presenter.loadMore());
                }
            }
        });
    }

    @Override
    protected FragmentTopTvShowsBinding initBinding(LayoutInflater inflater, @Nullable ViewGroup container) {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_top_tv_shows, container, false);
    }

    @Override
    protected TopShowsContract.Presenter presenter() {
        return presenter;
    }

    @Override
    protected TopShowsContract.View view() {
        return this;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        presenter.loadTopShows();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        state.loadingMore = false;
        removeLoadingView();
        super.onSaveInstanceState(outState);
    }

    @Override
    protected TopShowsState initializeState() {
        return new TopShowsState();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active) {
            binding.contentLoading.loading.setVisibility(View.VISIBLE);
            binding.contentView.setVisibility(View.GONE);
        } else {
            binding.contentView.setVisibility(View.VISIBLE);
            binding.contentLoading.loading.setVisibility(View.GONE);
        }
    }

    @Override
    public void showErrorMessage(String message) {
        showSnackbarWithMessage(message);
    }

    @Override
    public void updateItems(List<ShowShortEntity> entities) {
        state.topShows.clear();
        state.topShows.addAll(entities);
        topShowsAdapter.notifyItemsReplaced(entities);
    }

    @Override
    public void addItems(List<ShowShortEntity> entities) {
        topShowsAdapter.removeLoadingMore();

        state.topShows.addAll(entities);
        topShowsAdapter.notifyItemsAdded(entities);
    }

    @Override
    public void showSelected(ShowShortEntity entity) {

    }

    @Override
    public void addLoadingView() {
        topShowsAdapter.addLoadingMore();
    }

    @Override
    public void removeLoadingView() {
        topShowsAdapter.removeLoadingMore();
    }
}
