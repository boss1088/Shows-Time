package com.bosovskyi.showstime.data.interactor.shows;

import com.bosovskyi.showstime.data.source.api.ApiConstants;
import com.bosovskyi.showstime.data.source.api.ShowsApiService;
import com.bosovskyi.showstime.data.source.entity.ShowsResponseEntity;
import com.bosovskyi.showstime.tools.ObjectBuilder;
import com.bosovskyi.showstime.util.schedulers.BaseSchedulerProvider;
import com.bosovskyi.showstime.util.schedulers.TestSchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by boss1088 on 3/5/17.
 */
public class GetTopShowsInteractorTest {

    @Mock
    private ShowsApiService showsService;

    private GetTopShowsInteractor getTopShowsInteractor;
    private ShowsResponseEntity expectedSuccessResult;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        BaseSchedulerProvider schedulerProvider = new TestSchedulerProvider();
        getTopShowsInteractor = new GetTopShowsInteractor(showsService, schedulerProvider);
        expectedSuccessResult = ObjectBuilder.getTopShowsResponse();
    }

    @Test
    public void testGetTopShowsSuccess() {
        TestObserver<ShowsResponseEntity> testObserver = new TestObserver<>();
        when(showsService.getTvTopRated(ApiConstants.API_KEY)).thenReturn(Observable.just(expectedSuccessResult));

        getTopShowsInteractor.get().subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertResult(expectedSuccessResult);

        ShowsResponseEntity result = (ShowsResponseEntity) testObserver.getEvents().get(0).get(0);
        assertEquals(result.page, expectedSuccessResult.page);
        assertEquals(result.shows.size(), expectedSuccessResult.shows.size());
        assertEquals(result.totalPages, expectedSuccessResult.totalPages);
    }

}