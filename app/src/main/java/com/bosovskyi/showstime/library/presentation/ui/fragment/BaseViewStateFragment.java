package com.bosovskyi.showstime.library.presentation.ui.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bosovskyi.showstime.library.presentation.mvp.state.StateObject;
import com.bosovskyi.showstime.library.presentation.mvp.presenter.BaseStatePresenter;

/**
 * Created by boss1088 on 2/28/17.
 */

public abstract class BaseViewStateFragment<BINDING extends ViewDataBinding,
                                        STATE extends StateObject,
                                        PRESENTER extends BaseStatePresenter<STATE>>
        extends BaseViewFragment<BINDING, PRESENTER> {

    protected STATE state;

    private static final String STATE_OBJECT = "STATE";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            state = savedInstanceState.getParcelable(STATE_OBJECT);
        } else {
            state = initializeState();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getPresenter().setState(state);
    }

    protected abstract STATE initializeState();

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(STATE_OBJECT, state);
    }
}
