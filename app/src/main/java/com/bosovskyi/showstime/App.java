package com.bosovskyi.showstime;

import android.app.Application;

import com.bosovskyi.showstime.di.components.AppComponent;
import com.bosovskyi.showstime.di.components.DaggerAppComponent;
import com.bosovskyi.showstime.di.components.Injector;
import com.bosovskyi.showstime.di.modules.AndroidModule;
import com.frogermcs.dagger2metrics.Dagger2Metrics;

/**
 * Created by boss1088 on 2/23/17.
 */

public class App extends Application {

    private AppComponent component;

    public AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Dagger2Metrics.enableCapturing(this);
        }

        component = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .androidModule(new AndroidModule(this))
                .build();
    }

    public Injector getInjector() {
        return this.component
                .screenSubComponentBuilder()
                .build();
    }
}
