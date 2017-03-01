package com.bosovskyi.showstime.shows.top;

import com.bosovskyi.showstime.data.source.ShowsRepository;
import com.bosovskyi.showstime.util.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by boss1088 on 2/28/17.
 */

public class TopShowsPresenter implements TopShowsContract.Presenter {

    private final TopShowsContract.View mTopShowsView;
    private final ShowsRepository mShowsRepository;
    private final CompositeDisposable mDisposable;
    private final BaseSchedulerProvider mSchedulerProvider;

    TopShowsStateImpl mTopShowsState;

    @Inject
    TopShowsPresenter(TopShowsContract.View topShowsView, ShowsRepository repository,
                      BaseSchedulerProvider schedulerProvider) {
        mTopShowsView = topShowsView;
        mShowsRepository = repository;
        mSchedulerProvider = schedulerProvider;
        mDisposable = new CompositeDisposable();
    }

    @Override
    public void setState(TopShowsStateImpl topShowsState) {
        mTopShowsState = topShowsState;
    }

    @Override
    public void bind() {
        loadTopShows();
    }

    @Override
    public void loadTopShows() {
        mTopShowsView.setLoadingIndicator(true);

        mDisposable.add(
                mShowsRepository.getTopRatedShows()
                        .subscribeOn(mSchedulerProvider.computation())
                        .observeOn(mSchedulerProvider.ui())
                        .subscribe(
                                showsResponseEntity -> {
                                    mTopShowsState.page = showsResponseEntity.page;
                                    mTopShowsState.totalPages = showsResponseEntity.totalPages;
                                },
                                throwable -> {

                                },
                                () -> mTopShowsView.setLoadingIndicator(false)));
    }

    @Override
    public void unbind() {
        mDisposable.clear();
    }

}
