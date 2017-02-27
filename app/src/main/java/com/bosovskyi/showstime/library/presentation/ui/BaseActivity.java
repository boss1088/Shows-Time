package com.bosovskyi.showstime.library.presentation.ui;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by boss1088 on 2/9/17.
 */

public abstract class BaseActivity<BINDING extends ViewDataBinding> extends AppCompatActivity {

    protected BINDING binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = initBinding();
        setupToolbar();
    }

    private void setupToolbar() {
        setSupportActionBar(getToolbar());
    }

    protected abstract Toolbar getToolbar();

    protected abstract BINDING initBinding();
}
