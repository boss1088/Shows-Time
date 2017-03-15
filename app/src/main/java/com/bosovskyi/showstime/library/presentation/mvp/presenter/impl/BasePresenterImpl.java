package com.bosovskyi.showstime.library.presentation.mvp.presenter.impl;

import com.bosovskyi.showstime.library.presentation.mvp.presenter.BasePresenter;
import com.bosovskyi.showstime.library.presentation.mvp.view.BaseView;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by boss1088 on 3/2/17.
 */

public abstract class BasePresenterImpl<VIEW extends BaseView> implements BasePresenter<VIEW> {

    protected VIEW view;

    protected final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void bind(VIEW view) {
        this.view = view;
    }

    @Override
    public void unbind() {
        this.view = null;
        compositeDisposable.clear();
    }

    public VIEW view() {
        return view;
    }

    public boolean hasSubscriptions() {
        return compositeDisposable.size() != 0;
    }
}
