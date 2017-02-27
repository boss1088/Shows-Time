package com.bosovskyi.showstime.library.presentation.mvp.view.impl;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.os.IBinder;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.bosovskyi.showstime.library.presentation.mvp.view.BaseView;
import com.bosovskyi.showstime.library.presentation.util.ProgressDialogHelper;

/**
 * Created by boss1088 on 2/9/17.
 */

public abstract class BaseViewImpl implements BaseView {

    private Activity activity;

    private Fragment fragment;

    private View view;

    private Service service;

    private ProgressDialogHelper progressDialogHelper;

    public BaseViewImpl(Activity activity) {
        this.activity = activity;
        init();
    }

    public BaseViewImpl(Fragment fragment) {
        this.fragment = fragment;
        init();
    }

    public BaseViewImpl(View view) {
        this.view = view;
    }

    public BaseViewImpl(Service service) {
        this.service = service;
        init();
    }

    private void init() {
        progressDialogHelper = new ProgressDialogHelper();
    }

    @Override
    public abstract void showProgress();

    @Override
    public abstract void hideProgress();

    @Override
    public void showProgressDialog() {
        if (getContext() == null) {
            return;
        }
        progressDialogHelper.showProgress(getContext());
    }

    @Override
    public void hideProgressDialog() {
        progressDialogHelper.hideProgress();
    }

    @Override
    public void showErrorMessage(String message) {
        if (getSnackBarBackground() == null) {
            return;
        }
        Snackbar.make(getSnackBarBackground(), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage(@StringRes int message) {
        if (getContext() == null) {
            return;
        }
        showErrorMessage(getContext().getString(message));
    }

    @Override
    public void hideKeyboard() {
        if (getContext() == null || getWindowToken() == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindowToken(), 0);
    }

    private Context getContext() {
        if (activity != null) {
            return activity;
        } else if (fragment != null) {
            return fragment.getContext();
        } else if (view != null) {
            return view.getContext();
        } else if (service != null) {
            return service;
        }
        return null;
    }

    private View getSnackBarBackground() {
        if (activity != null) {
            return activity.findViewById(android.R.id.content);
        } else if (view != null) {
            return view;
        } else if (fragment != null) {
            return fragment.getView();
        }
        return null;
    }

    private IBinder getWindowToken() {
        if (activity != null) {
            View view = activity.getCurrentFocus();
            return view == null ? null : view.getWindowToken();
        } else if (fragment != null) {
            Activity activity = fragment.getActivity();
            if (activity == null) {
                return null;
            }
            View view = activity.getCurrentFocus();
            return view == null ? null : view.getWindowToken();
        } else if (view != null) {
            return view.getWindowToken();
        }
        return null;
    }
}
