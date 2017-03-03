package com.bosovskyi.showstime.library.presentation.ui.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.bosovskyi.showstime.App;
import com.bosovskyi.showstime.di.components.Injector;

/**
 * Created by boss1088 on 3/2/17.
 */

public abstract class BaseActivity<BINDING extends ViewDataBinding> extends AppCompatActivity {

    protected BINDING binding;

    private Injector injector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initializeActivityComponent();
        super.onCreate(savedInstanceState);

        binding = initBinding();
        setupToolbar();
    }

    private void setupToolbar() {
        if (getToolbar() != null) {
            setSupportActionBar(getToolbar());
        }
    }

    public Injector getInjector() {
        return this.injector;
    }

    private void initializeActivityComponent() {
        if (this.injector == null) {
            this.injector = ((App)getApplication()).getInjector();
        }
    }

    protected abstract Toolbar getToolbar();

    protected abstract BINDING initBinding();
}
