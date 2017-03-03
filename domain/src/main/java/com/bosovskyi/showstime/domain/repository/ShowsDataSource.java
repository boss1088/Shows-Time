package com.bosovskyi.showstime.domain.repository;

import com.bosovskyi.showstime.domain.entity.ShowsResponseEntity;

import io.reactivex.Observable;

/**
 * Created by boss1088 on 2/27/17.
 */

public interface ShowsDataSource {

    Observable<ShowsResponseEntity> getTopRatedShows();
}
