package com.bosovskyi.showstime.screens.shows;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.bosovskyi.showstime.R;
import com.bosovskyi.showstime.databinding.ActivityShowsTabBinding;
import com.bosovskyi.showstime.library.presentation.ui.activity.BaseBindingActivity;
import com.bosovskyi.showstime.screens.shows.top.TopShowsFragment;
import com.bosovskyi.showstime.util.ActivityUtils;

public class ShowsTabActivity extends BaseBindingActivity<ActivityShowsTabBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TopShowsFragment fragment =
                (TopShowsFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            fragment = TopShowsFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), fragment, R.id.contentFrame);
        }
    }

    @Override
    protected Toolbar getToolbar() {
        return binding.toolbar;
    }

    @Override
    protected ActivityShowsTabBinding initBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_shows_tab);
    }
}
