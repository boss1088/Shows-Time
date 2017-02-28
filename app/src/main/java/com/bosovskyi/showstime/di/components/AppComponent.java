package com.bosovskyi.showstime.di.components;

import com.bosovskyi.showstime.di.modules.AndroidModule;
import com.bosovskyi.showstime.di.modules.ShowsApiModule;
import com.bosovskyi.showstime.di.modules.ShowsRepositoryModule;
import com.bosovskyi.showstime.di.scopes.AppScope;

import dagger.Component;

/**
 * Created by boss1088 on 2/27/17.
 */

@AppScope
@Component (modules = {AndroidModule.class, ShowsApiModule.class, ShowsRepositoryModule.class})
public interface AppComponent {

    TopShowsComponent.Builder topShowsComponentBuilder();

}
