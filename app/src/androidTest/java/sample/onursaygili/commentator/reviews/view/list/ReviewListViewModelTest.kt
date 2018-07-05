package sample.onursaygili.commentator.reviews.view.list

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import kotlinx.android.synthetic.main.review_list_fragment.view.*
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import sample.onursaygili.commentator.CustomAssertions.Companion.hasItemCount
import sample.onursaygili.commentator.R

class ReviewListViewModelTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule<ReviewListActivity>(ReviewListActivity::class.java)

    @Test
    fun getReviews() {
        Thread.sleep(1500)
        onView(withId(R.id.recycler_view))
                .perform()
                .check(hasItemCount(100))
    }
}