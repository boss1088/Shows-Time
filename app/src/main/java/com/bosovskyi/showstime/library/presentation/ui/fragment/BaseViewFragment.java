package com.bosovskyi.showstime.library.presentation.ui.fragment;

import android.content.Context;
import android.databinding.ViewDataBinding;

import com.bosovskyi.showstime.library.presentation.mvp.presenter.BasePresenter;

/**
 * Created by boss1088 on 2/17/17.
 */
public abstract class BaseViewFragment<BINDING extends ViewDataBinding,
                                        PRESENTER extends BasePresenter,
                                        COMPONENT>
        extends BaseFragment<BINDING> {

    protected COMPONENT mComponent;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mComponent = injectComponent(context);
    }

    @Override
    public void onDestroyView() {
        getPresenter().unbind();
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        mComponent = null;
        super.onDetach();
    }

    protected abstract COMPONENT injectComponent(Context context);

    protected abstract PRESENTER getPresenter();
}
