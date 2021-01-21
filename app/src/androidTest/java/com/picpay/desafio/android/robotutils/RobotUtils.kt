package com.picpay.desafio.android.robotutils

import android.app.Activity
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import com.picpay.desafio.android.RecyclerViewMatchers

object RobotUtils {

    fun isRecyclerViewVisible(recyclerViewId: Int) {
        onView(withId(recyclerViewId)).check(matches(isDisplayed()))
    }

    fun isScroll(recyclerViewId: Int, position: Int) {
        onView(withId(recyclerViewId)).perform(scrollToPosition<RecyclerView.ViewHolder>(position))
    }

    private fun recyclerViewMatcher(recyclerViewId: Int): RecyclerViewMatchers {
        return RecyclerViewMatchers(recyclerViewId)

    }

    fun checkIfTextIsDisplayedOnRecyclerView(id: Int, position: Int, text: String) {
        onView(recyclerViewMatcher(id).atPosition(position))
            .check(matches(hasDescendant(withText(text))))
    }

    fun scroolTheLastPositionOfRecyclerView(activity: Activity, @IdRes recyclerViewId: Int) {
        val recyclerView = activity.findViewById<RecyclerView>(recyclerViewId)
        val item = recyclerView.adapter?.itemCount as Int
        onView(withId(recyclerViewId)).perform(scrollToPosition<RecyclerView.ViewHolder>(-1))
    }
}