package com.bosovskyi.showstime.domain.interactors;

import com.bosovskyi.showstime.domain.schedulers.BaseSchedulerProvider;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;

/**
 * Created by boss1088 on 3/3/17.
 */

public abstract class UseCase<T> {

    private final BaseSchedulerProvider schedulerProvider;

    protected Disposable disposable = Disposables.empty();

    public UseCase(BaseSchedulerProvider schedulerProvider) {
        this.schedulerProvider = schedulerProvider;
    }

    protected abstract Observable<T> buildUseCaseObservable();

    public <S extends Observer<T> & Disposable> void execute(S useCaseDisposable) {
        this.disposable = this.buildUseCaseObservable()
                .subscribeOn(schedulerProvider.computation())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(useCaseDisposable);
    }

    public void unsubscribe() {
        if (!this.disposable.isDisposed()) {
            this.disposable.dispose();
        }
    }

    public boolean isUnsubscribed() {
        return this.disposable.isDisposed();
    }
}
