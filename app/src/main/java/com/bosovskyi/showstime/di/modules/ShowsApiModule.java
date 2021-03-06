package com.bosovskyi.showstime.di.modules;

import com.bosovskyi.showstime.data.source.api.ShowsApiService;
import com.bosovskyi.showstime.di.scopes.AppScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by boss1088 on 2/13/17.
 */

@Module
public class ShowsApiModule {

    private final static String URL = "https://api.themoviedb.org/3/";

    @Provides
    @AppScope
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(URL)
                .build();
    }

    @Provides
    @AppScope
    ShowsApiService provideTvShowService(Retrofit retrofit) {
        return retrofit.create(ShowsApiService.class);
    }
}
