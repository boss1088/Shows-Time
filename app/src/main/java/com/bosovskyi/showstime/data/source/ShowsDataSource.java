package com.bosovskyi.showstime.data.source;

import com.bosovskyi.showstime.data.source.entity.ShowsResponseEntity;

import io.reactivex.Observable;

/**
 * Created by boss1088 on 2/27/17.
 */

public interface ShowsDataSource {

    Observable<ShowsResponseEntity> getTopRatedShows();

    void refresh();
}
