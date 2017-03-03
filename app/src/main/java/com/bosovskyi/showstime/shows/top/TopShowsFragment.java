package com.bosovskyi.showstime.shows.top;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bosovskyi.showstime.R;
import com.bosovskyi.showstime.databinding.FragmentTopTvShowsBinding;
import com.bosovskyi.showstime.library.presentation.ui.fragment.BaseStateFragment;

import javax.inject.Inject;

/**
 * Created by boss1088 on 2/28/17.
 */

public class TopShowsFragment
        extends BaseStateFragment<FragmentTopTvShowsBinding, TopShowsContract.View, TopShowsStateImpl, TopShowsContract.Presenter>
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

        //TODO:
        binding.resultText.setText(String.valueOf(state.page));
    }

    @Override
    protected TopShowsStateImpl initializeState() {
        return new TopShowsStateImpl();
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }
}
