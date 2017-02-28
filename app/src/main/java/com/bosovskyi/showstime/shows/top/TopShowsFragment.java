package com.bosovskyi.showstime.shows.top;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bosovskyi.showstime.App;
import com.bosovskyi.showstime.R;
import com.bosovskyi.showstime.databinding.FragmentTopTvShowsBinding;
import com.bosovskyi.showstime.di.components.DaggerTopShowsComponent;
import com.bosovskyi.showstime.di.modules.TopShowsPresenterModule;
import com.bosovskyi.showstime.library.presentation.ui.fragment.BaseViewStateFragment;

import javax.inject.Inject;

/**
 * Created by boss1088 on 2/28/17.
 */

public class TopShowsFragment extends BaseViewStateFragment<FragmentTopTvShowsBinding, TopShowsStateImpl, TopShowsContract.Presenter>
        implements TopShowsContract.View {

    @Inject
    TopShowsPresenter mPresenter;

    @Inject
    Resources resources;

    public static TopShowsFragment newInstance() {
        return new TopShowsFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void injectComponent(Context context) {
        DaggerTopShowsComponent.builder()
                .appComponent(((App) context.getApplicationContext()).getComponent())
                .topShowsPresenterModule(new TopShowsPresenterModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected FragmentTopTvShowsBinding initBinding(LayoutInflater inflater, @Nullable ViewGroup container) {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_top_tv_shows, container, false);
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
    protected TopShowsContract.Presenter getPresenter() {
        return mPresenter;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
