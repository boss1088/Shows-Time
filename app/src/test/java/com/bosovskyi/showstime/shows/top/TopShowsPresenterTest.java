package com.bosovskyi.showstime.shows.top;

import com.bosovskyi.showstime.data.source.ShowsRepository;
import com.bosovskyi.showstime.domain.entity.ShowShortEntity;
import com.bosovskyi.showstime.domain.entity.ShowsResponseEntity;
import com.bosovskyi.showstime.util.schedulers.TestSchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

/**
 * Created by boss1088 on 2/28/17.
 */
public class TopShowsPresenterTest {

    @Mock
    TopShowsContract.View mView;

    @Mock
    ShowsRepository mRepository;

    private TopShowsPresenter presenter;

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

        presenter = new TopShowsPresenter(mRepository, new TestSchedulerProvider());
        presenter.bind(mView, new TopShowsStateImpl());
    }

    @Test
    public void loadTopItems() {
        when(mRepository.getTopRatedShows()).thenReturn(Observable.just(expectedResult));

        presenter.loadTopShows();

        verify(presenter.view()).setLoadingIndicator(true);

        verify(presenter.view()).setLoadingIndicator(false);

        assertEquals(presenter.state().page, 1);
    }

}