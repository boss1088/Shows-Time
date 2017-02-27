package com.bosovskyi.showstime.library.presentation.ui;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by boss1088 on 2/9/17.
 */

public abstract class BaseFragment
        <BINDING extends ViewDataBinding>
        extends Fragment {

    protected BINDING binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = initBinding(inflater, container);
        return binding.getRoot();
    }

    protected abstract BINDING initBinding(LayoutInflater inflater, @Nullable ViewGroup container);
}
