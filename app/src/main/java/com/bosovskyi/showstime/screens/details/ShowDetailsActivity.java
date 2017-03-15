package com.bosovskyi.showstime.screens.details;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.Toolbar;
import android.transition.AutoTransition;
import android.transition.Fade;
import android.view.View;

import com.bosovskyi.showstime.R;
import com.bosovskyi.showstime.data.source.entity.ShowShortEntity;
import com.bosovskyi.showstime.databinding.ActivityShowDetailBinding;
import com.bosovskyi.showstime.library.presentation.ui.activity.BaseBindingActivity;
import com.bosovskyi.showstime.util.ActivityUtils;

/**
 * Created by boss1088 on 3/13/17.
 */

public class ShowDetailsActivity extends BaseBindingActivity<ActivityShowDetailBinding> {

    public static void navigate(Activity activity, ShowShortEntity entity, View coverView) {
        Intent intent = new Intent(activity, ShowDetailsActivity.class);
        intent.putExtra("show", entity);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, coverView, "image");
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initActivityTransitions();

        setupBackActivity();

        supportPostponeEnterTransition();

        ShowDetailsFragment fragment =
                (ShowDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (fragment == null) {
            fragment = new ShowDetailsFragment();
            fragment.setArguments(getIntent().getExtras());
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), fragment, R.id.contentFrame);
        }
    }

    @Override
    protected Toolbar getToolbar() {
        return binding.toolbar;
    }

    @Override
    protected ActivityShowDetailBinding initBinding() {
        return DataBindingUtil.setContentView(this, R.layout.activity_show_detail);
    }

    public void setToolbarColor(@ColorInt int color) {
        getToolbar().setBackgroundColor(color);
    }

    private void initActivityTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Fade transitionIn = new Fade(Fade.IN);
            Fade transitionOut = new Fade(Fade.OUT);
            AutoTransition transition = new AutoTransition();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setEnterTransition(transitionIn);
            getWindow().setReturnTransition(transitionOut);
        }
    }
}
