package com.bosovskyi.showstime.library.presentation.mvp.presenter;

import com.bosovskyi.showstime.library.presentation.mvp.state.StateObject;
import com.bosovskyi.showstime.library.presentation.mvp.view.BaseView;

/**
 * Created by boss1088 on 3/2/17.
 */

public interface StatePresenter<VIEW extends BaseView, STATE extends StateObject>
        extends Presenter<VIEW> {

    void bind(VIEW view, STATE state);

}
