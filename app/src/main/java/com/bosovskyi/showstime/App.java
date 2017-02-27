package com.bosovskyi.showstime;

import android.app.Application;

import com.bosovskyi.showstime.di.components.AppComponent;
import com.bosovskyi.showstime.di.components.DaggerAppComponent;
import com.bosovskyi.showstime.di.modules.AndroidModule;

/**
 * Created by boss1088 on 2/23/17.
 */

public class App extends Application {

    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        component = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .androidModule(new AndroidModule(this))
                .build();
    }
}
