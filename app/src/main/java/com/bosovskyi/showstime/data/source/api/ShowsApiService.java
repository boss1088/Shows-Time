package com.bosovskyi.showstime.data.source.api;

import com.bosovskyi.showstime.data.source.entity.ShowsResponseEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by boss1088 on 2/27/17.
 */

public interface ShowsApiService {

    String QUERY_API_KEY = "api_key";
    String QUERY_PAGE = "page";

    String API_TV_TOP_RATED = "tv/top_rated";

    String API_TV_POPULAR = "tv/popular";

    @GET(API_TV_TOP_RATED)
    Observable<ShowsResponseEntity> getTvTopRated(
            @Query(QUERY_API_KEY) String apiKey,
            @Query(QUERY_PAGE) Integer page
    );

    @GET(API_TV_POPULAR)
    Observable<ShowsResponseEntity> getTvPopular(
            @Query(QUERY_API_KEY) String apiKey,
            @Query(QUERY_PAGE) Integer page
    );

}
