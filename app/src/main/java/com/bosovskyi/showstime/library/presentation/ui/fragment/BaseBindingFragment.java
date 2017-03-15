package com.bosovskyi.showstime.library.presentation.ui.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by boss1088 on 3/15/17.
 */

public abstract class BaseBindingFragment<BINDING extends ViewDataBinding> extends Fragment {

    protected BINDING binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = initBinding(inflater, container);
        return binding.getRoot();
    }

    protected abstract BINDING initBinding(LayoutInflater inflater, @Nullable ViewGroup container);

    protected void showSnackbarWithMessage(@StringRes int message) {
        if (getView() != null) {
            Snackbar.make(getView(), getResources().getString(message), Snackbar.LENGTH_SHORT);
        }
    }

    protected void showSnackbarWithMessage(String message) {
        if (getView() != null) {
            Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT);
        }
    }
}
