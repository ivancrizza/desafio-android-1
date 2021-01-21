package com.picpay.desafio.android

import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class RecyclerViewMatchers(private val recyclerViewId: Int) {

    fun atPosition(position: Int): Matcher<View> {
        return positionOnAView(position, -1)
    }


    private fun positionOnAView(position: Int, viewId: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            var view: View? = null
            var resources: Resources? = null


            override fun describeTo(description: Description?) {
                var descritionId = recyclerViewId.toString()
                if (this.resources != null) {
                    try {
                        descritionId = this.resources?.getResourceName(recyclerViewId).toString()
                    } catch (var4: Resources.NotFoundException) {
                        descritionId = String.format(
                            "%s (o resource específico não foi encontrado)",
                            Integer.valueOf(recyclerViewId)
                        )
                    }
                    description?.appendText("with id: $descritionId")
                }
            }

            override fun matchesSafely(item: View?): Boolean {

                this.resources = view?.resources

                if (view == null) {
                    val recyclerView =
                        view?.rootView?.findViewById(recyclerViewId) as RecyclerView
                    if (recyclerView.id == recyclerViewId) {
                        view =
                            recyclerView.findViewHolderForAdapterPosition(position)?.itemView
                    } else {
                        return false
                    }
                }

                return if (viewId == -1) {
                    view === view
                } else {
                    val targetView = view?.findViewById<View>(viewId)
                    view === targetView
                }
            }


        }


    }
}