package com.zeltixdev.comicapp

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import org.hamcrest.Matcher

fun viewWait(delay: Long) {
    onView(isRoot()).perform(waitFor(delay))
}

fun waitFor(delay: Long):
        ViewAction = object : ViewAction {
     override fun perform(
         uiController: UiController?, view: View?
     ) { uiController?.loopMainThreadForAtLeast(delay) }
      override fun getConstraints(): Matcher<View> = isRoot()
      override fun getDescription():
             String = "wait for " + delay + "milliseconds"
    }