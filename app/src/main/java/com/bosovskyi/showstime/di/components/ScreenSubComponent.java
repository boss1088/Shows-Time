package com.bosovskyi.showstime.di.components;

import com.bosovskyi.showstime.di.scopes.ScreenScope;

import dagger.Subcomponent;

/**
 * Created by boss1088 on 3/2/17.
 */

@ScreenScope
@Subcomponent
public interface ScreenSubComponent extends Injector {

    @Subcomponent.Builder
    interface Builder {
        ScreenSubComponent build();
    }
}
