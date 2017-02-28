package com.bosovskyi.showstime.library.presentation.mvp.presenter;

import com.bosovskyi.showstime.library.presentation.mvp.state.StateObject;

/**
 * Created by boss1088 on 2/28/17.
 */

public interface BaseStatePresenter<STATE extends StateObject> extends BasePresenter {

    void setState(STATE state);

}
