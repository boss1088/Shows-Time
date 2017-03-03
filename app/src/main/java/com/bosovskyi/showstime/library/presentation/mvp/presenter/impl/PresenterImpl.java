package com.bosovskyi.showstime.library.presentation.mvp.presenter.impl;

import com.bosovskyi.showstime.library.presentation.mvp.presenter.Presenter;
import com.bosovskyi.showstime.library.presentation.mvp.view.BaseView;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by boss1088 on 3/2/17.
 */

public abstract class PresenterImpl<VIEW extends BaseView> implements Presenter<VIEW> {

    protected VIEW view;

    protected final CompositeDisposable disposable = new CompositeDisposable();

    @Override
    public void bind(VIEW view) {
        this.view = view;
    }

    @Override
    public void unbind() {
        this.view = null;
        disposable.clear();
    }

    public VIEW view() {
        return view;
    }
}
