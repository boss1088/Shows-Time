package com.bosovskyi.showstime.library.presentation.mvp.presenter;

import com.bosovskyi.showstime.library.presentation.mvp.view.BaseView;

/**
 * Created by boss1088 on 3/2/17.
 */

public interface Presenter<VIEW extends BaseView> {

    void bind(VIEW view);
    void unbind();

}
