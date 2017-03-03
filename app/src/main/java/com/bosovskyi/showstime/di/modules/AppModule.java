package com.bosovskyi.showstime.di.modules;

import com.bosovskyi.showstime.di.scopes.AppScope;
import com.bosovskyi.showstime.domain.schedulers.BaseSchedulerProvider;
import com.bosovskyi.showstime.util.schedulers.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by boss1088 on 2/28/17.
 */

@Module
public class AppModule {

    @Provides
    @AppScope
    public BaseSchedulerProvider baseSchedulerProvider() {
        return new SchedulerProvider();
    }
}
