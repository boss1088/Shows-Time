package com.bosovskyi.showstime;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.bosovskyi.showstime.library.presentation.ui.activity.BaseBindingActivity;

/**
 * Created by boss1088 on 3/1/17.
 */
@VisibleForTesting
public class TestActivity extends BaseBindingActivity<ViewDataBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setId(R.id.contentFrame);
        setContentView(frameLayout);
    }

    @Override
    protected Toolbar getToolbar() {
        return null;
    }

    @Override
    protected ViewDataBinding initBinding() {
        return null;
    }
}
