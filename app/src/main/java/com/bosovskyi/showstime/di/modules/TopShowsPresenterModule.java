package com.bosovskyi.showstime.di.modules;

import com.bosovskyi.showstime.shows.top.TopShowsContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by boss1088 on 2/28/17.
 */

@Module
public class TopShowsPresenterModule {

    private final TopShowsContract.View mView;

    public TopShowsPresenterModule(TopShowsContract.View view) {
        mView = view;
    }

    @Provides
    TopShowsContract.View provideTopShowsView() {
        return mView;
    }
}
