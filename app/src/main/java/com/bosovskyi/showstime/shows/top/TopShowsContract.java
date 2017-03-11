package com.bosovskyi.showstime.shows.top;

import com.bosovskyi.showstime.data.source.entity.ShowShortEntity;
import com.bosovskyi.showstime.library.presentation.mvp.presenter.StatePresenter;
import com.bosovskyi.showstime.library.presentation.mvp.view.BaseView;

import java.util.List;

/**
 * Created by boss1088 on 2/28/17.
 */

public interface TopShowsContract {

    interface View extends BaseView {

        void setLoadingIndicator(boolean active);

        void showErrorMessage(String message);

        void updateItems(List<ShowShortEntity> entities);

        void addItems(List<ShowShortEntity> entities);

        void showSelected(ShowShortEntity entity);

        void addLoadingView();

        void removeLoadingView();
    }

    interface Presenter extends StatePresenter<View, TopShowsState> {
        void loadTopShows();

        void loadMore();
    }
}
