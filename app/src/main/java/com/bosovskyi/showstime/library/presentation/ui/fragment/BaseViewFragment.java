package com.bosovskyi.showstime.library.presentation.ui.fragment;

import android.content.Context;
import android.databinding.ViewDataBinding;

import com.bosovskyi.showstime.library.presentation.mvp.presenter.BasePresenter;
import com.bosovskyi.showstime.library.presentation.ui.fragment.BaseFragment;

import dagger.Component;

/**
 * Created by boss1088 on 2/17/17.
 */
public abstract class BaseViewFragment<BINDING extends ViewDataBinding,
                                        PRESENTER extends BasePresenter>
        extends BaseFragment<BINDING> {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        injectComponent(context);
    }

    @Override
    public void onDestroyView() {
        getPresenter().unbind();
        super.onDestroyView();
    }

    protected abstract void injectComponent(Context context);

    protected abstract PRESENTER getPresenter();
}
