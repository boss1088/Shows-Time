package com.bosovskyi.showstime.library.presentation.ui.fragment;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bosovskyi.showstime.di.components.Injector;
import com.bosovskyi.showstime.library.presentation.mvp.presenter.BasePresenter;
import com.bosovskyi.showstime.library.presentation.mvp.view.BaseView;
import com.bosovskyi.showstime.library.presentation.ui.activity.BaseBindingActivity;

/**
 * Created by boss1088 on 3/2/17.
 */

public abstract class BaseMvpFragment<BINDING extends ViewDataBinding,
                                    VIEW extends BaseView,
                                    PRESENTER extends BasePresenter<VIEW>>
        extends BaseBindingFragment<BINDING> {

    protected abstract void callInjection();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callInjection();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (presenter() != null) {
            presenter().bind(view());
        }
    }

    @Override
    public void onDestroyView() {
        if (presenter() != null) {
            presenter().unbind();
        }
        super.onDestroyView();
    }

    protected Injector getInjector() {
        return ((BaseBindingActivity)getActivity()).getInjector();
    }

    protected abstract PRESENTER presenter();

    protected abstract VIEW view();
}
