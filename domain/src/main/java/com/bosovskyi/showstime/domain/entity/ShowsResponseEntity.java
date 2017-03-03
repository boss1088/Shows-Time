package com.bosovskyi.showstime.domain.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by boss1088 on 2/27/17.
 */

public class ShowsResponseEntity {

    public ShowsResponseEntity() {}

    public ShowsResponseEntity(int page, List<ShowShortEntity> shows, int totalPages) {
        this.page = page;
        this.shows = shows;
        this.totalPages = totalPages;
    }

    @SerializedName("page")
    @Expose
    public int page;

    @SerializedName("results")
    public List<ShowShortEntity> shows = null;

    @SerializedName("total_pages")
    public int totalPages;

}
