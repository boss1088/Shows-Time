package com.bosovskyi.showstime.di.components;

import com.bosovskyi.showstime.di.modules.TopShowsPresenterModule;
import com.bosovskyi.showstime.di.scopes.ScreenScope;
import com.bosovskyi.showstime.shows.top.TopShowsFragment;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by boss1088 on 2/28/17.
 */

@ScreenScope
@Subcomponent(modules = TopShowsPresenterModule.class)
public interface TopShowsComponent {

    @Subcomponent.Builder
    interface Builder {
        TopShowsComponent.Builder topShowsPresenterModule(
                TopShowsPresenterModule topShowsPresenterModule);
        TopShowsComponent build();
    }

    void inject(TopShowsFragment fragment);

}
