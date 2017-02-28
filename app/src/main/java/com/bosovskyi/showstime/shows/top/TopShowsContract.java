package com.bosovskyi.showstime.shows.top;

import com.bosovskyi.showstime.library.presentation.mvp.presenter.BaseStatePresenter;
import com.bosovskyi.showstime.library.presentation.mvp.view.BaseView;

/**
 * Created by boss1088 on 2/28/17.
 */

public interface TopShowsContract {

    interface View extends BaseView {

    }

    interface Presenter extends BaseStatePresenter<TopShowsStateImpl> {
        void loadTopShows();
    }
}
