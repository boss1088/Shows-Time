package com.bosovskyi.showstime.tools;

import com.bosovskyi.showstime.domain.entity.ShowShortEntity;
import com.bosovskyi.showstime.domain.entity.ShowsResponseEntity;

import java.util.ArrayList;

/**
 * Created by boss1088 on 3/3/17.
 */

public class ObjectHelper {

    public static ShowsResponseEntity getTopShowsResponse() {
        ShowsResponseEntity showsResponseEntity = new ShowsResponseEntity();
        showsResponseEntity.page = 1;
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
