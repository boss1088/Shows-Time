package com.bosovskyi.showstime.di.modules;

import android.content.Context;
import android.content.res.Resources;

import com.bosovskyi.showstime.App;
import com.bosovskyi.showstime.di.scopes.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by boss1088 on 2/27/17.
 */

@Module
public class AndroidModule {

    private App application;

    public AndroidModule(App application) {
        this.application = application;
    }

    @Provides
    @AppScope
    Context provideContext() {
        return application.getApplicationContext();
    }

    @Provides
    @AppScope
    Resources provideResources() {
        return application.getResources();
    }
}
