package com.bosovskyi.showstime.shows.top;

import com.bosovskyi.showstime.data.interactor.shows.GetTopShowsInteractor;
import com.bosovskyi.showstime.data.source.entity.ShowShortEntity;
import com.bosovskyi.showstime.data.source.entity.ShowsResponseEntity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

/**
 * Created by boss1088 on 2/28/17.
 */
public class TopShowsPresenterTest {

    @Mock
    TopShowsContract.View view;

    @Mock
    GetTopShowsInteractor interactor;

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

        presenter = new TopShowsPresenter(interactor);
        presenter.bind(view, new TopShowsState());
    }

    @Test
    public void testLoadTopShowsSuccess() {
        when(interactor.get()).thenReturn(Observable.just(expectedResult));

        presenter.loadTopShows();

        verify(presenter.view()).setLoadingIndicator(true);

        verify(presenter.view()).updateItems(expectedResult.shows);

        verify(presenter.view()).setLoadingIndicator(false);

        assertEquals(presenter.state().page, 1);
    }

    @Test
    public void testLoadTopShowsAfterRotate() {
        given(interactor.get()).willReturn(Observable.just(expectedResult));

        presenter.loadTopShows();

        verify(presenter.view()).setLoadingIndicator(true);

        verify(presenter.view()).updateItems(expectedResult.shows);

        verify(presenter.view()).setLoadingIndicator(false);

        assertEquals(presenter.state().page, 1);
        assertEquals(presenter.state().loadedFirstTime, true);

        presenter.unbind();
        presenter.bind(view, presenter.state());
        presenter.loadTopShows();

        assertEquals(presenter.state().loadedFirstTime, true);
        assertEquals(presenter.hasSubscriptions(), false);
    }

    @Test
    public void testLoadTopShowsError() {
        Exception exception = new Exception("Exception");
        when(interactor.get()).thenReturn(Observable.error(exception));

        presenter.loadTopShows();

        verify(presenter.view()).setLoadingIndicator(true);

        verify(presenter.view()).showErrorMessage(exception.getMessage());

        verify(presenter.view()).setLoadingIndicator(false);
    }

}