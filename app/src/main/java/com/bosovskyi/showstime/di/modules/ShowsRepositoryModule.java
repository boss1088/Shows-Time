package com.bosovskyi.showstime.di.modules;

import com.bosovskyi.showstime.data.source.ShowsDataSource;
import com.bosovskyi.showstime.data.source.api.ShowsApiService;
import com.bosovskyi.showstime.data.source.remote.ShowsRemoteDataSource;
import com.bosovskyi.showstime.di.scopes.AppScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by boss1088 on 2/28/17.
 */

@Module
public class ShowsRepositoryModule {

    @AppScope
    @Provides
    ShowsDataSource provideRemoteDataSource(ShowsApiService apiService) {
        return new ShowsRemoteDataSource(apiService);
    }
}
