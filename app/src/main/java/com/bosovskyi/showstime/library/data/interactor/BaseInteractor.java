package com.bosovskyi.showstime.library.data.interactor;

import com.bosovskyi.showstime.util.schedulers.BaseSchedulerProvider;

import io.reactivex.Observable;

/**
 * Created by boss1088 on 3/4/17.
 */

public abstract class BaseInteractor<RESPONSE_DATA, REQUEST_DATA> {

    private final BaseSchedulerProvider schedulerProvider;

    public BaseInteractor(BaseSchedulerProvider schedulerProvider) {
        this.schedulerProvider = schedulerProvider;
    }

    protected abstract Observable<RESPONSE_DATA> buildUseCaseObservable(REQUEST_DATA data);

    public Observable<RESPONSE_DATA> get(REQUEST_DATA data) {
        return this.buildUseCaseObservable(data)
                .subscribeOn(schedulerProvider.computation())
                .subscribeOn(schedulerProvider.ui());
    }
}
