package com.bosovskyi.showstime.data.source.remote;

import com.bosovskyi.showstime.data.source.entity.ShowShortEntity;
import com.bosovskyi.showstime.data.source.entity.ShowsResponseEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by boss1088 on 2/27/17.
 */
public class ShowsRemoteDataSourceTest {


    ShowsApiService service;
    String apiKey;

    ShowsRemoteDataSource remoteDataSource;
    ShowsResponseEntity expectedResult;

    @Before
    public void setUp() throws Exception {
        service = mock(ShowsApiService.class);
        apiKey = "mockApiKey";

        ShowShortEntity showShortEntity = new ShowShortEntity();
        showShortEntity.name = "Test name";
        showShortEntity.id = 1;

        List<ShowShortEntity> list = new ArrayList<>();
        list.add(showShortEntity);

        expectedResult = new ShowsResponseEntity();
        expectedResult.shows = list;
        expectedResult.page = 1;
        expectedResult.totalPages = 1;
        expectedResult.totalResults = 1;

        remoteDataSource = new ShowsRemoteDataSource(service, apiKey);
    }

    @Test
    public void getTopRatedShowsTest() {
        TestObserver<ShowsResponseEntity> testObserver = new TestObserver<>();

        when(service.getTvTopRated(apiKey)).thenReturn(Observable.just(expectedResult));

        remoteDataSource.getTopRatedShows()
                .subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertResult(expectedResult);
    }

    @After
    public void tearDown() throws Exception {

    }

}