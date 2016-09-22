package com.icapps.mvp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import com.icapps.mvp.view.CommentsActivity;
import com.icapps.mvp.view.PostsActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by maartenvangiel on 21/09/16.
 */

public class PostsActivityTest {

    @Rule
    public IntentsTestRule mActivityRule = new IntentsTestRule<>(PostsActivity.class);

    @Test public void testPostsActivity(){
        onView(withId(R.id.rcv_posts)).check(matches(isDisplayed()));
        onView(withId(R.id.rcv_posts)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        intended(hasComponent(CommentsActivity.class.getName()));
    }

}
