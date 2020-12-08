package com.zeltixdev.comicapp

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.zeltixdev.comicapp.ui.MainActivity
import org.junit.Test
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class SimpleInstrumentationTest {

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
     fun recyclerClickNavigatesToDetail() {
        viewWait(5000)
        onView(withId(R.id.comic_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.detail_title))
                .check(matches(isAssignableFrom(TextView::class.java)))
    }

}