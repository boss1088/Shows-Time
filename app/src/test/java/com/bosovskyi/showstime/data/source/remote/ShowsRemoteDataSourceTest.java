package com.bosovskyi.showstime.data.source.remote;

import com.bosovskyi.showstime.data.source.api.ApiConstants;
import com.bosovskyi.showstime.data.source.api.ShowsApiService;
import com.bosovskyi.showstime.data.source.entity.ShowShortEntity;
import com.bosovskyi.showstime.data.source.entity.ShowsResponseEntity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by boss1088 on 2/27/17.
 */
public class ShowsRemoteDataSourceTest {

    @Mock
    ShowsApiService service;

    private ShowsRemoteDataSource remoteDataSource;
    private ShowsResponseEntity expectedResult;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        ShowShortEntity showShortEntity = new ShowShortEntity();
        showShortEntity.name = "Test name";
        showShortEntity.id = 1;

        List<ShowShortEntity> list = new ArrayList<>();
        list.add(showShortEntity);

        expectedResult = new ShowsResponseEntity();
        expectedResult.shows = list;
        expectedResult.page = 1;
        expectedResult.totalPages = 1;

        remoteDataSource = new ShowsRemoteDataSource(service);
    }

    @Test
    public void getTopRatedShowsTest() {
        TestObserver<ShowsResponseEntity> testObserver = new TestObserver<>();

        when(service.getTvTopRated(ApiConstants.API_KEY, null)).thenReturn(Observable.just(expectedResult));

        remoteDataSource.getTopRatedShows()
                .subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertResult(expectedResult);
    }

}