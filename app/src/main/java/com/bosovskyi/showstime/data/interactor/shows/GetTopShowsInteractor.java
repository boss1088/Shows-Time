package com.bosovskyi.showstime.data.interactor.shows;

import com.bosovskyi.showstime.library.data.interactor.BaseInteractor;
import com.bosovskyi.showstime.library.data.interactor.BaseVoidInteractor;
import com.bosovskyi.showstime.data.source.api.ApiConstants;
import com.bosovskyi.showstime.data.source.entity.ShowsResponseEntity;
import com.bosovskyi.showstime.data.source.api.ShowsApiService;
import com.bosovskyi.showstime.util.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by boss1088 on 3/4/17.
 */

public class GetTopShowsInteractor extends BaseInteractor<ShowsResponseEntity, Integer> {

    private final ShowsApiService showsService;

    @Inject
    public GetTopShowsInteractor(ShowsApiService showsService, BaseSchedulerProvider schedulerProvider) {
        super(schedulerProvider);
        this.showsService = showsService;
    }

    protected Observable<ShowsResponseEntity> buildUseCaseObservable(Integer page) {
        return showsService.getTvTopRated(ApiConstants.API_KEY, page);
    }

}
