package com.bosovskyi.showstime.shows.top;

import com.bosovskyi.showstime.data.interactor.shows.GetTopShowsInteractor;
import com.bosovskyi.showstime.data.source.entity.ShowShortEntity;
import com.bosovskyi.showstime.library.presentation.mvp.presenter.impl.StatePresenterImpl;

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
            topShowsLoaded();
            return;
        }

        load();
    }

    public void topShowsLoaded() {
        view.updateItems(state.topShows);
        view.setLoadingIndicator(false);
    }

    public void load() {
        compositeDisposable.add(
                getTopShowsInteractor.get()
                        .doOnSubscribe(disposable -> view.setLoadingIndicator(true))
                        .doOnTerminate(() -> view.setLoadingIndicator(false))
                        .subscribe(
                                showsResponseEntity -> {
                                    state.page = showsResponseEntity.page;
                                    state.totalPages = showsResponseEntity.totalPages;
                                    view.updateItems(showsResponseEntity.shows);
                                    state.loadedFirstTime = true;
                                },
                                throwable -> view.showErrorMessage(throwable.getMessage())));
    }
}
