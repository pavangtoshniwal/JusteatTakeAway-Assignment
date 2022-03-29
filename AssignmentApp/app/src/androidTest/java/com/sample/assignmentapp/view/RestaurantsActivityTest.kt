package com.sample.assignmentapp.view

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sample.assignmentapp.R
import com.sample.assignmentapp.utils.waitUntilViewIsDisplayed
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RestaurantsActivityTest{

    @get:Rule
    var activityRule= ActivityScenarioRule(RestaurantsActivity::class.java)

    @Test
    fun testRecyclerViewVisible(){
        onView(withId(R.id.progress_bar))
            .check(matches(isDisplayed()))
        waitUntilViewIsDisplayed(withId(R.id.restaurant_rv))
        onView(withId(R.id.restaurant_rv))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testRecyclerViewFirstItemDisplayed() {
        onView(withId(R.id.progress_bar))
            .check(matches(isDisplayed()))
        waitUntilViewIsDisplayed(withId(R.id.restaurant_rv))
        onView(withText("Tanoshii Sushi")).check(matches(isDisplayed()))
    }

    @Test
    fun testRecyclerViewPerformClick() {
        onView(withId(R.id.progress_bar))
            .check(matches(isDisplayed()))
        waitUntilViewIsDisplayed(withId(R.id.restaurant_rv))
        onView(withId(R.id.restaurant_rv)).perform(RecyclerViewActions
            .actionOnItemAtPosition<RecyclerView.ViewHolder>(3, click()))
    }

    @Test
    fun testRecyclerViewScrollToLastItemDisplayed() {
        onView(withId(R.id.progress_bar))
            .check(matches(isDisplayed()))
        waitUntilViewIsDisplayed(withId(R.id.restaurant_rv))
        onView(withId(R.id.restaurant_rv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(18))
        onView(withText("Zenzai Sushi")).check(matches(isDisplayed()))
    }

    @Test
    fun testRecyclerViewLastItemNotDisplayed() {
        onView(withId(R.id.progress_bar))
            .check(matches(isDisplayed()))
        waitUntilViewIsDisplayed(withId(R.id.restaurant_rv))
        onView(withText("Zenzai Sushi")).check(doesNotExist())
    }

    @Test
    fun testMenuItemClick() {
        onView(withId(R.id.progress_bar))
            .check(matches(isDisplayed()))
        waitUntilViewIsDisplayed(withId(R.id.restaurant_rv))
        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext())
        onView(withText("Best Match")).perform(click())
    }


}