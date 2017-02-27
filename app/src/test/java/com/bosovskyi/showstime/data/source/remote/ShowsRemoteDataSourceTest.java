package com.bosovskyi.showstime.data.source.remote;

import com.bosovskyi.showstime.data.source.entity.ShowShortEntity;
import com.bosovskyi.showstime.data.source.entity.ShowsResponseEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.observers.TestObserver;

/**
 * Created by boss1088 on 2/27/17.
 */
public class ShowsRemoteDataSourceTest {

    @Mock
    ShowsApiService service;

    @Mock
    String apiKey;

    ShowsRemoteDataSource remoteDataSource;
    ShowsResponseEntity expectedResult;

    @Before
    public void setUp() throws Exception {
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

        remoteDataSource.getTopRatedShows()
                .subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertResult(expectedResult);
    }

    @After
    public void tearDown() throws Exception {

    }

}