package com.bosovskyi.showstime.data.source;

import com.bosovskyi.showstime.di.scopes.AppScope;
import com.bosovskyi.showstime.domain.entity.ShowsResponseEntity;
import com.bosovskyi.showstime.domain.repository.ShowsDataSource;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by boss1088 on 2/27/17.
 */

@AppScope
public class ShowsRepository implements ShowsDataSource {

    private final ShowsDataSource mRemoteDataSource;

    @Inject
    public ShowsRepository(ShowsDataSource remoteDataSource) {
        this.mRemoteDataSource = remoteDataSource;
    }

    @Override
    public Observable<ShowsResponseEntity> getTopRatedShows() {
        return mRemoteDataSource.getTopRatedShows();
    }
}
