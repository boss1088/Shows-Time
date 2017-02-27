package com.bosovskyi.showstime.library.presentation.mvp.view;


import android.support.annotation.IntegerRes;

/**
 * Created by boss1088 on 2/9/17.
 */

public interface BaseView {

    void showProgress();

    void showProgressDialog();

    void hideProgress();

    void hideProgressDialog();

    void hideKeyboard();

    void showErrorMessage(String message);

    void showErrorMessage(@IntegerRes int message);

}
