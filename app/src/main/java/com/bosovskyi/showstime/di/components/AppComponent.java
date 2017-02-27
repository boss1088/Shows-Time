package com.bosovskyi.showstime.di.components;

import com.bosovskyi.showstime.di.modules.AndroidModule;

import dagger.Component;

/**
 * Created by boss1088 on 2/27/17.
 */

@Component (modules = {AndroidModule.class, })
public interface AppComponent {
}
