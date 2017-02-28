package com.bosovskyi.showstime.shows.top;

import com.bosovskyi.showstime.data.source.ShowsRepository;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by boss1088 on 2/28/17.
 */

public class TopShowsPresenter implements TopShowsContract.Presenter {

    private final TopShowsContract.View mTopShowsView;
    private final ShowsRepository mShowsRepository;
    private final CompositeDisposable mDisposable;

    TopShowsStateImpl mTopShowsState;

    @Inject
    TopShowsPresenter(TopShowsContract.View topShowsView, ShowsRepository repository) {
        mTopShowsView = topShowsView;
        mShowsRepository = repository;
        mDisposable = new CompositeDisposable();
    }

    @Override
    public void setState(TopShowsStateImpl topShowsState) {
        mTopShowsState = topShowsState;
    }

    @Override
    public void unbind() {
        mDisposable.clear();
    }

}
