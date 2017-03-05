package com.bosovskyi.showstime.tools;

import com.bosovskyi.showstime.data.source.entity.ShowShortEntity;
import com.bosovskyi.showstime.data.source.entity.ShowsResponseEntity;

import java.util.ArrayList;

/**
 * Created by boss1088 on 3/5/17.
 */

public class ObjectBuilder {

    public static ShowsResponseEntity getTopShowsResponse() {
        ShowsResponseEntity showsResponseEntity = new ShowsResponseEntity();
        showsResponseEntity.page = 1;
        showsResponseEntity.totalPages = 2;
        showsResponseEntity.shows = new ArrayList<>();
        showsResponseEntity.shows.add(getShortShowInfo());
        showsResponseEntity.shows.add(getShortShowInfo());

        return showsResponseEntity;
    }

    private static ShowShortEntity getShortShowInfo() {
        ShowShortEntity showShortEntity = new ShowShortEntity();
        showShortEntity.name = "Shows Time";
        return showShortEntity;
    }
}
