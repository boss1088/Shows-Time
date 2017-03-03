package com.bosovskyi.showstime.shows.top;

import android.content.Intent;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.bosovskyi.showstime.FragmentTestRule;
import com.bosovskyi.showstime.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by boss1088 on 3/1/17.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class TopShowsFragmentTest {


    @Rule
    public FragmentTestRule<TopShowsFragment> mFragmentTestRule = new FragmentTestRule<>(TopShowsFragment.class);

    @Before
    public void setUp() {

    }

    @Test
    public void setLoadingIndicator() throws Exception {
        mFragmentTestRule.launchActivity(new Intent());
        onView(withId(R.id.resultText)).check(matches(isDisplayed()));
    }

}