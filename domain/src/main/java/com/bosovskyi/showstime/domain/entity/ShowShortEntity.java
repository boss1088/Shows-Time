package com.bosovskyi.showstime.domain.entity;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowShortEntity implements Serializable {

    @SerializedName("poster_path")
    @Expose
    public String posterPath;

    @SerializedName("popularity")
    @Expose
    public double popularity;

    @SerializedName("id")
    @Expose
    public long id;

    @SerializedName("backdrop_path")
    @Expose
    public String backdropPath;

    @SerializedName("vote_average")
    @Expose
    public double voteAverage;

    @SerializedName("overview")
    @Expose
    public String overview;

    @SerializedName("first_air_date")
    @Expose
    public String firstAirDate;

    @SerializedName("origin_country")
    @Expose
    public List<String> originCountry = null;

    @SerializedName("genre_ids")
    @Expose
    public List<Long> genreIds = null;

    @SerializedName("original_language")
    @Expose
    public String originalLanguage;

    @SerializedName("vote_count")
    @Expose
    public long voteCount;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("original_name")
    @Expose
    public String originalName;

}
