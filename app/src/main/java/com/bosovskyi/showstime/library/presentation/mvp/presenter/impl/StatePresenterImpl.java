package com.bosovskyi.showstime.library.presentation.mvp.presenter.impl;

import com.bosovskyi.showstime.library.presentation.mvp.presenter.StatePresenter;
import com.bosovskyi.showstime.library.presentation.mvp.state.StateObject;
import com.bosovskyi.showstime.library.presentation.mvp.view.BaseView;

/**
 * Created by boss1088 on 3/2/17.
 */

public abstract class StatePresenterImpl<VIEW extends BaseView, STATE extends StateObject>
        extends PresenterImpl<VIEW>
        implements StatePresenter<VIEW, STATE> {

    protected STATE state;

    @Override
    public void bind(VIEW view, STATE state) {
        this.view = view;
        this.state = state;
    }

    public STATE state() {
        return state;
    }
}
