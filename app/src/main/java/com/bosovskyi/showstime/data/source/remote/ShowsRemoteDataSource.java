package com.bosovskyi.showstime.data.source.remote;

import com.bosovskyi.showstime.data.source.ShowsDataSource;
import com.bosovskyi.showstime.data.source.entity.ShowsResponseEntity;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by boss1088 on 2/27/17.
 */

public class ShowsRemoteDataSource implements ShowsDataSource {

    private final ShowsApiService mShowsService;

    private final String mApiKey;

    @Inject
    public ShowsRemoteDataSource(ShowsApiService service, String apiKey) {
        mShowsService = service;
        mApiKey = apiKey;
    }

    @Override
    public Observable<ShowsResponseEntity> getTopRatedShows() {
        return mShowsService.getTvTopRated(mApiKey);
    }
}
