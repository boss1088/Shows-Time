package com.bosovskyi.showstime.domain.interactors.shows;

import com.bosovskyi.showstime.domain.entity.ShowsResponseEntity;
import com.bosovskyi.showstime.domain.repository.ShowsDataSource;
import com.bosovskyi.showstime.domain.schedulers.BaseSchedulerProvider;
import com.bosovskyi.showstime.domain.schedulers.TestSchedulerProvider;
import com.bosovskyi.showstime.tools.ObjectHelper;
import com.bosovskyi.showstime.tools.TestUtils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by boss1088 on 3/3/17.
 */
public class GetShowsUseCaseTest {

    @Mock
    private ShowsDataSource showsRepository;

    private BaseSchedulerProvider schedulerProvider;

    private GetShowsUseCase getShowsUseCase;

    private TestUtils testUtils = new TestUtils();

    private ShowsResponseEntity responseEntity;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        schedulerProvider = new TestSchedulerProvider();

        getShowsUseCase = new GetShowsUseCase(schedulerProvider, showsRepository);

        responseEntity = ObjectHelper.getTopShowsResponse();
    }

    @Test
    public void testGetTopShowsUseCaseSuccess() {
        TestObserver<ShowsResponseEntity> testObserver = new TestObserver<>();
        when(showsRepository.getTopRatedShows()).thenReturn(Observable.just(responseEntity));

        getShowsUseCase.buildUseCaseObservable().subscribe(testObserver);

        ShowsResponseEntity answer = (ShowsResponseEntity) testObserver.getEvents().get(0).get(0);

        assertEquals(answer.page, 1);
        assertEquals(answer.shows.size(), 2);
        verify(showsRepository).getTopRatedShows();
        verifyNoMoreInteractions(showsRepository);
    }

}