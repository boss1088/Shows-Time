package com.bosovskyi.showstime.data.source;

import com.bosovskyi.showstime.data.source.entity.ShowShortEntity;
import com.bosovskyi.showstime.data.source.entity.ShowsResponseEntity;
import com.bosovskyi.showstime.di.scopes.AppScope;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by boss1088 on 2/27/17.
 */

@AppScope
public class ShowsRepository implements ShowsDataSource {

    private final ShowsDataSource mRemoteDataSource;

    Map<Long, ShowShortEntity> showsMap;
    int page = 1;
    int totalPages = 1;

    boolean mCacheIsDirty = false;

    @Inject
    public ShowsRepository(ShowsDataSource remoteDataSource) {
        this.mRemoteDataSource = remoteDataSource;
    }

    @Override
    public Observable<ShowsResponseEntity> getTopRatedShows() {
        if (showsMap != null && !mCacheIsDirty) {
            return Observable.just(new ShowsResponseEntity(page, new ArrayList<>(showsMap.values()), totalPages));
        }

        return mRemoteDataSource.getTopRatedShows()
                .doOnNext(this::saveShows);
    }

    @Override
    public void refresh() {
        mCacheIsDirty = true;
        showsMap = null;
        page = 1;
        totalPages = 1;
    }

    private void saveShows(ShowsResponseEntity showsResponseEntity) {
        if (showsMap == null) {
            showsMap = new LinkedHashMap<>();
        }

        if (showsResponseEntity.page == 1) {
            showsMap.clear();
        }

        for (ShowShortEntity showShortEntity:showsResponseEntity.shows) {
            showsMap.put(showShortEntity.id, showShortEntity);
        }
        page = showsResponseEntity.page;
        totalPages = showsResponseEntity.totalPages;
        mCacheIsDirty = false;
    }
}
