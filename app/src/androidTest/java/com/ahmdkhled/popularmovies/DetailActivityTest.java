package com.ahmdkhled.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.ahmdkhled.popularmovies.activities.DetailActivity;
import com.ahmdkhled.popularmovies.activities.MainActivity;
import com.ahmdkhled.popularmovies.model.Movie;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Ahmed Khaled on 7/12/2018.
 */

@RunWith(JUnit4.class)
public class DactivityTest {

    @Rule
    public ActivityTestRule<DetailActivity> activityTestRule=
            new ActivityTestRule<DetailActivity>(DetailActivity.class){

        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent=new Intent(targetContext,DetailActivity.class);
            intent.putExtra("movie",new Movie(363088,"test title","overview","fdf"
                    ,"vcv",10));
            return intent;
        }
            };


    @Test
    public void test(){
        onView(withId(R.id.details_pager)).check(matches(isDisplayed()));
    }


}
