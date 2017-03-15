package com.bosovskyi.showstime.screens.shows.top;

import com.bosovskyi.showstime.data.interactor.shows.GetTopShowsInteractor;
import com.bosovskyi.showstime.data.source.entity.ShowsResponseEntity;
import com.bosovskyi.showstime.tools.ObjectBuilder;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
    private ShowsResponseEntity expectedMoreResult;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        expectedResult = ObjectBuilder.getShowsFirstTimeResponse();
        expectedMoreResult = ObjectBuilder.getMoreShowsResponse();

        presenter = new TopShowsPresenter(interactor);
        presenter.bind(view, new TopShowsState());
    }

    @Test
    public void testLoadTopShowsSuccess() {
        when(interactor.get(null)).thenReturn(Observable.just(expectedResult));

        presenter.loadTopShows();

        verify(presenter.view()).setLoadingIndicator(true);

        verify(presenter.view()).updateItems(expectedResult.shows);

        verify(presenter.view()).setLoadingIndicator(false);

        assertEquals(presenter.state().page, 1);
    }

    @Test
    public void testLoadMoreShowsSuccess() {
        when(interactor.get(null)).thenReturn(Observable.just(expectedResult));

        presenter.loadTopShows();

        verify(presenter.view()).setLoadingIndicator(true);

        verify(presenter.view()).updateItems(expectedResult.shows);

        verify(presenter.view()).setLoadingIndicator(false);

        assertEquals(presenter.state().page, 1);

        when(interactor.get(presenter.state().page + 1)).thenReturn(Observable.just(expectedMoreResult));

        presenter.loadMore();

        verify(presenter.view()).addLoadingView();

        verify(presenter.view()).addItems(expectedMoreResult.shows);

        assertEquals(presenter.state().page, 2);
    }

    @Test
    public void testLoadTopShowsAfterRotate() {
        given(interactor.get(null)).willReturn(Observable.just(expectedResult));

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
        when(interactor.get(null)).thenReturn(Observable.error(exception));

        presenter.loadTopShows();

        verify(presenter.view()).setLoadingIndicator(true);

        verify(presenter.view()).showErrorMessage(exception.getMessage());

        verify(presenter.view()).setLoadingIndicator(false);
    }

}