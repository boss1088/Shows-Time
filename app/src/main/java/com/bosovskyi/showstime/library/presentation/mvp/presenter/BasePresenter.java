package com.bosovskyi.showstime.library.presentation.mvp.presenter;

import com.android.annotations.NonNull;
import com.bosovskyi.showstime.library.presentation.mvp.view.BaseView;

/**
 * Created by boss1088 on 2/17/17.
 */

public interface BasePresenter<VIEW extends BaseView> {

    void bind(@NonNull VIEW view);

    void unbind();

}
