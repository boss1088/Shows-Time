package com.bosovskyi.showstime.library.presentation.ui.fragment;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bosovskyi.showstime.di.components.Injector;
import com.bosovskyi.showstime.library.presentation.mvp.presenter.Presenter;
import com.bosovskyi.showstime.library.presentation.mvp.view.BaseView;
import com.bosovskyi.showstime.library.presentation.ui.activity.BaseActivity;

/**
 * Created by boss1088 on 3/2/17.
 */

public abstract class BaseFragment<BINDING extends ViewDataBinding,
                                    VIEW extends BaseView,
                                    PRESENTER extends Presenter<VIEW>>
        extends Fragment {

    protected BINDING binding;

    protected abstract void callInjection();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callInjection();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = initBinding(inflater, container);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter().bind(view());
    }

    @Override
    public void onDestroyView() {
        presenter().unbind();
        super.onDestroyView();
    }

    protected Injector getInjector() {
        return ((BaseActivity)getActivity()).getInjector();
    }

    protected abstract BINDING initBinding(LayoutInflater inflater, @Nullable ViewGroup container);

    protected abstract PRESENTER presenter();

    protected abstract VIEW view();
}
