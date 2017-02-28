package com.bosovskyi.showstime.data.source;

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

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by boss1088 on 2/27/17.
 */
public class ShowsRepositoryTest {

    @Mock
    ShowsDataSource remoteDataSource;

    ShowsRepository showsRepository;

    ShowsResponseEntity expectedResult;

    private TestObserver<ShowsResponseEntity> testShowsObserver = new TestObserver<>();

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

        testShowsObserver = new TestObserver<>();

        showsRepository = new ShowsRepository(remoteDataSource);
    }

    @Test
    public void getTopRatedShows_repositoryCachesAfterFirstSubscription_whenShowsAvailableOnRemoteStorage() {
        setShowsAvailable(remoteDataSource);

        TestObserver<ShowsResponseEntity> testObserver = new TestObserver<>();

        showsRepository.getTopRatedShows().subscribe(testObserver);

        verify(remoteDataSource).getTopRatedShows();
        testObserver.assertValues(expectedResult);
    }

    private void setShowsAvailable(ShowsDataSource dataSource) {
        when(dataSource.getTopRatedShows()).thenReturn(Observable.just(expectedResult));
    }

    private void setShowsNotAvailable(ShowsDataSource dataSource) {
        when(dataSource.getTopRatedShows()).thenReturn(Observable.just(new ShowsResponseEntity()));
    }

}