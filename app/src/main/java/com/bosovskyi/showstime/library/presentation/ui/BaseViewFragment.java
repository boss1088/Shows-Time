package com.bosovskyi.showstime.library.presentation.ui;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bosovskyi.showstime.library.presentation.mvp.presenter.BasePresenter;
import com.bosovskyi.showstime.library.presentation.mvp.view.BaseView;

/**
 * Created by boss1088 on 2/17/17.
 */
public abstract class BaseViewFragment<BINDING extends ViewDataBinding,
                VIEW extends BaseView,
                PRESENTER extends BasePresenter<VIEW>>
        extends BaseFragment<BINDING> {

    protected VIEW view;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        view = initView();
        injectPresenterComponent();
        getPresenter().bind(view);
    }

    @Override
    public void onDestroyView() {
        getPresenter().unbind();
        super.onDestroyView();
    }

    protected abstract PRESENTER getPresenter();

    protected abstract VIEW initView();

    protected abstract void injectPresenterComponent();
}
