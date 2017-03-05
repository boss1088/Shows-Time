package com.bosovskyi.showstime.library.data.interactor;

import com.bosovskyi.showstime.util.schedulers.BaseSchedulerProvider;
import com.bosovskyi.showstime.util.schedulers.TestSchedulerProvider;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;

/**
 * Created by boss1088 on 3/5/17.
 */
public class BaseVoidInteractorTest extends BaseInteractorTest {

    private FakeInteractor fakeInteractor;

    @Before
    public void setUp() {
        schedulerProvider = new TestSchedulerProvider();
        fakeInteractor = new FakeInteractor(schedulerProvider);
    }

    @Test
    public void testGetObservable() {
        TestObserver<Integer> testObserver = new TestObserver<>();

        this.fakeInteractor.get().subscribe(testObserver);

        testObserver.assertNoErrors();
        testObserver.assertResult(1,2,3);
    }

    private static class FakeInteractor extends BaseVoidInteractor<Integer> {

        FakeInteractor(BaseSchedulerProvider schedulerProvider) {
            super(schedulerProvider);
        }

        @Override
        protected Observable<Integer> buildUseCaseObservable() {
            return Observable.just(1,2,3);
        }


    }

}