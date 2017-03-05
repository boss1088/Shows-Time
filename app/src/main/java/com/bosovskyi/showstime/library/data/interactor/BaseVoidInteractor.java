package com.bosovskyi.showstime.library.data.interactor;

import com.bosovskyi.showstime.util.schedulers.BaseSchedulerProvider;

import io.reactivex.Observable;

/**
 * Created by boss1088 on 3/4/17.
 */

public abstract class BaseVoidInteractor<RESPONSE_DATA> extends BaseInteractor<RESPONSE_DATA, Void> {

    public BaseVoidInteractor(BaseSchedulerProvider schedulerProvider) {
        super(schedulerProvider);
    }

    @Override
    protected Observable<RESPONSE_DATA> buildUseCaseObservable(Void aVoid) {
        return buildUseCaseObservable();
    }

    protected abstract Observable<RESPONSE_DATA> buildUseCaseObservable();

    public Observable<RESPONSE_DATA> get() {
        return super.get(null);
    }
}
