package com.ahmdkhled.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.action.CloseKeyboardAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;

import com.ahmdkhled.popularmovies.activities.DetailActivity;
import com.ahmdkhled.popularmovies.activities.MainActivity;
import com.ahmdkhled.popularmovies.model.Movie;
import com.ahmdkhled.popularmovies.network.Urls;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Ahmed Khaled on 7/12/2018.
 */

@RunWith(JUnit4.class)
public class DetailActivityTest {

    @Rule
    public ActivityTestRule<DetailActivity> activityTestRule=
            new ActivityTestRule<DetailActivity>(DetailActivity.class){

        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent intent=new Intent(targetContext,DetailActivity.class);
            intent.putExtra("movie",new Movie(299536,"Avengers: Infinity War",
                    " this is a test overview",
                    "fdf"
                    ,"/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
                    8.4));
            return intent;
        }
            };


    @Before
    public void initIntents(){
        Intents.init();
    }


    @Test
    public void testWidgetsDisplay(){
        onView(withId(R.id.details_pager)).check(matches(isDisplayed()));
        onView(withId(R.id.movie_cover)).check(matches(isDisplayed()));
        onView(withId(R.id.vote)).check(matches(isDisplayed()));
    }

    @Test
    public void testMovieDetails(){
        onView(withId(R.id.movie_name)).check(matches(not(withText(""))));
        onView(withId(R.id.overview)).check(matches(not(withText(""))));
        onView(withId(R.id.release_date)).check(matches(not(withText(""))));
    }

    @Test
    public void testTrailersClicking()  {
        goToTraillersTab();

        onView(withId(R.id.traillers_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        intended(allOf(hasAction(Intent.ACTION_VIEW),hasData(Uri.parse(Urls.getYoutubeUrl("6ZfuNTqbHE8"))) ));
    }

    @After
    public void releaseIntents(){
        Intents.release();
    }


    public void goToTraillersTab() {
        try {
            activityTestRule.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    activityTestRule.getActivity().tab.getTabAt(1).select();

                }
            });
        }catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
