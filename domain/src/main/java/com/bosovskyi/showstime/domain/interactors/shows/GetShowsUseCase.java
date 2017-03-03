package com.bosovskyi.showstime.domain.interactors.shows;

import com.bosovskyi.showstime.domain.entity.ShowsResponseEntity;
import com.bosovskyi.showstime.domain.interactors.UseCase;
import com.bosovskyi.showstime.domain.repository.ShowsDataSource;
import com.bosovskyi.showstime.domain.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by boss1088 on 3/3/17.
 */

public class GetShowsUseCase extends UseCase<ShowsResponseEntity> {
    
    private ShowsDataSource showsRepository;

    @Inject
    public GetShowsUseCase(BaseSchedulerProvider schedulerProvider, ShowsDataSource showsRepository) {
        super(schedulerProvider);
        this.showsRepository = showsRepository;
    }

    @Override
    protected Observable<ShowsResponseEntity> buildUseCaseObservable() {
        return showsRepository.getTopRatedShows();
    }

}
