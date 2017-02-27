
package com.bosovskyi.showstime.data.source.entity;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowShortEntity implements Parcelable {

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


    public final static Creator<ShowShortEntity> CREATOR = new Creator<ShowShortEntity>() {

        @SuppressWarnings({
            "unchecked"
        })
        public ShowShortEntity createFromParcel(Parcel in) {
            ShowShortEntity instance = new ShowShortEntity();
            instance.posterPath = ((String) in.readValue((String.class.getClassLoader())));
            instance.popularity = ((double) in.readValue((double.class.getClassLoader())));
            instance.id = ((long) in.readValue((long.class.getClassLoader())));
            instance.backdropPath = ((String) in.readValue((String.class.getClassLoader())));
            instance.voteAverage = ((double) in.readValue((double.class.getClassLoader())));
            instance.overview = ((String) in.readValue((String.class.getClassLoader())));
            instance.firstAirDate = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.originCountry, (String.class.getClassLoader()));
            in.readList(instance.genreIds, (Long.class.getClassLoader()));
            instance.originalLanguage = ((String) in.readValue((String.class.getClassLoader())));
            instance.voteCount = ((long) in.readValue((long.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.originalName = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public ShowShortEntity[] newArray(int size) {
            return (new ShowShortEntity[size]);
        }

    }
    ;

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(posterPath);
        dest.writeValue(popularity);
        dest.writeValue(id);
        dest.writeValue(backdropPath);
        dest.writeValue(voteAverage);
        dest.writeValue(overview);
        dest.writeValue(firstAirDate);
        dest.writeList(originCountry);
        dest.writeList(genreIds);
        dest.writeValue(originalLanguage);
        dest.writeValue(voteCount);
        dest.writeValue(name);
        dest.writeValue(originalName);
    }

    public int describeContents() {
        return  0;
    }

}
