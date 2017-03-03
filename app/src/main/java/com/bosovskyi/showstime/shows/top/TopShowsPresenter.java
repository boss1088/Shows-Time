package com.bosovskyi.showstime.shows.top;

import com.bosovskyi.showstime.data.source.ShowsRepository;
import com.bosovskyi.showstime.library.presentation.mvp.presenter.impl.StatePresenterImpl;
import com.bosovskyi.showstime.domain.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;

/**
 * Created by boss1088 on 2/28/17.
 */

public class TopShowsPresenter extends StatePresenterImpl<TopShowsContract.View, TopShowsStateImpl>
        implements TopShowsContract.Presenter {

    private final ShowsRepository mShowsRepository;
    private final BaseSchedulerProvider mSchedulerProvider;

    @Inject
    TopShowsPresenter(ShowsRepository repository,
                      BaseSchedulerProvider schedulerProvider) {
        mShowsRepository = repository;
        mSchedulerProvider = schedulerProvider;
    }

    @Override
    public void bind(TopShowsContract.View view, TopShowsStateImpl topShowsState) {
        super.bind(view, topShowsState);
    }

    @Override
    public void loadTopShows() {
        view.setLoadingIndicator(true);

        disposable.add(
                mShowsRepository.getTopRatedShows()
                        .subscribeOn(mSchedulerProvider.computation())
                        .observeOn(mSchedulerProvider.ui())
                        .subscribe(
                                showsResponseEntity -> {
                                    state.page = showsResponseEntity.page;
                                    state.totalPages = showsResponseEntity.totalPages;
                                },
                                throwable -> {

                                },
                                () -> view.setLoadingIndicator(false)));
    }
}
