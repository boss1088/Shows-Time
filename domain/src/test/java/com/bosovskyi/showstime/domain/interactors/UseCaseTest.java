package com.bosovskyi.showstime.domain.interactors;

import com.bosovskyi.showstime.domain.schedulers.BaseSchedulerProvider;
import com.bosovskyi.showstime.domain.schedulers.TestSchedulerProvider;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by boss1088 on 3/3/17.
 */
public class UseCaseTest {

    private TestObserver<Integer> testObserver;
    private FakeUseCase fakeUseCase;
    private BaseSchedulerProvider schedulerProvider;

    @Before
    public void setUp() {
        this.schedulerProvider = new TestSchedulerProvider();
        this.testObserver = new TestObserver<>();
        this.fakeUseCase = new FakeUseCase(schedulerProvider);
    }

    @Test
    public void testUseCaseExecutionResult() {
        TestScheduler testScheduler = new TestScheduler();

        this.fakeUseCase.execute(testObserver);
        testScheduler.triggerActions();

        this.testObserver.assertNoErrors();
        this.testObserver.assertResult(1, 2, 3);
    }

    @Test
    public void testUseCaseUnsubscription() {
        this.fakeUseCase.execute(testObserver);
        assertEquals(this.fakeUseCase.isUnsubscribed(), false);

        this.fakeUseCase.unsubscribe();
        assertEquals(this.fakeUseCase.isUnsubscribed(), true);
    }

    private static class FakeUseCase extends UseCase<Integer> {

        protected FakeUseCase(BaseSchedulerProvider schedulerProvider) {
            super(schedulerProvider);
        }

        @Override protected Observable<Integer> buildUseCaseObservable() {
            return Observable.just(1, 2, 3);
        }

    }

}