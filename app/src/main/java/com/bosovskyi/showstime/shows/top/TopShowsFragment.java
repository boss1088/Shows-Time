package com.bosovskyi.showstime.shows.top;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

    public static TopShowsFragment newInstance() {
        return new TopShowsFragment();
    }

    @Override
    protected void callInjection() {
        getInjector().inject(this);
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

        presenter().loadTopShows();
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
        state.topShows = entities;
        binding.resultText.setText(state.topShows.get(0).name);
    }

    @Override
    public void addItems(List<ShowShortEntity> entities) {

    }
}
