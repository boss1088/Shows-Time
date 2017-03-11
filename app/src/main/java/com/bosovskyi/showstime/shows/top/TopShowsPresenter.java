package com.bosovskyi.showstime.shows.top;

import com.bosovskyi.showstime.data.interactor.shows.GetTopShowsInteractor;
import com.bosovskyi.showstime.data.source.entity.ShowShortEntity;
import com.bosovskyi.showstime.library.presentation.mvp.presenter.impl.StatePresenterImpl;
import com.bosovskyi.showstime.util.EspressoIdlingResource;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by boss1088 on 2/28/17.
 */

public class TopShowsPresenter extends StatePresenterImpl<TopShowsContract.View, TopShowsState>
        implements TopShowsContract.Presenter {

    private final GetTopShowsInteractor getTopShowsInteractor;

    @Inject
    TopShowsPresenter(GetTopShowsInteractor getTopShowsInteractor) {
        this.getTopShowsInteractor = getTopShowsInteractor;
    }

    @Override
    public void bind(TopShowsContract.View view, TopShowsState topShowsState) {
        super.bind(view, topShowsState);
    }

    @Override
    public void loadTopShows() {
        if (state.loadedFirstTime) {
            view.setLoadingIndicator(false);
            return;
        }

        load();
    }

    public void load() {
        EspressoIdlingResource.increment();
        compositeDisposable.add(
                getTopShowsInteractor.get(null)
                        .doOnSubscribe(disposable -> view.setLoadingIndicator(true))
                        .doFinally(() -> {
                            if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                                EspressoIdlingResource.decrement();
                            }

                            if (view != null) {
                                view.setLoadingIndicator(false);
                            }
                        })
                        .subscribe(
                                showsResponseEntity -> {
                                    state.page = showsResponseEntity.page;
                                    state.totalPages = showsResponseEntity.totalPages;
                                    if (view != null) {
                                        view.updateItems(showsResponseEntity.shows);
                                    }
                                    state.loadedFirstTime = true;
                                },
                                throwable -> view.showErrorMessage(throwable.getMessage())));
    }

    @Override
    public void loadMore() {
        if (state.loadingMore || state.page == state.totalPages) {
            return;
        }

        state.loadingMore = true;
        compositeDisposable.add(
                getTopShowsInteractor.get(state.page + 1)
                        .doOnSubscribe(disposable -> view.addLoadingView())
                        .doFinally(() -> state.loadingMore = false)
                        .subscribe(
                                showsResponseEntity -> {
                                    state.page = showsResponseEntity.page;
                                    state.totalPages = showsResponseEntity.totalPages;
                                    view.addItems(showsResponseEntity.shows);
                                },
                                throwable -> {
                                    view.removeLoadingView();
                                    view.showErrorMessage(throwable.getMessage());
                                }));
    }
}
