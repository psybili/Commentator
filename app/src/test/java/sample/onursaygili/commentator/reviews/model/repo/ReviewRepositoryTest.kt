package sample.onursaygili.commentator.reviews.model.repo

import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import sample.onursaygili.commentator.RxImmediateSchedulerRule
import sample.onursaygili.commentator.reviews.model.Review
import sample.onursaygili.commentator.reviews.model.local.ReviewDao
import sample.onursaygili.commentator.reviews.model.remote.ReviewQueryParams
import sample.onursaygili.commentator.reviews.model.remote.ReviewResponse
import sample.onursaygili.commentator.reviews.model.remote.ReviewService


class ReviewRepositoryTest {

    @Rule
    @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var service: ReviewService

    @Mock
    private lateinit var dao: ReviewDao

    private lateinit var repository: ReviewRepository

    @Before
    fun setUp() {
        repository = ReviewRepository(service, dao)
    }

    @Test
    fun getRemoteReviewsShouldReturnRemoteReviewList() {
        val r1 = Review.randomReview()
        val r2 = Review.randomReview()
        val r3 = Review.randomReview()
        val params = ReviewQueryParams(0, 0, 0, "", "")

        Mockito.`when`(service.listReviews(params.count, params.count, params.rating, params.sortBy, params.direction))
                .thenReturn(Flowable.just(ReviewResponse(false, 3, listOf(r1, r2, r3))))

        val result = repository.getRemoteReviews(params)

        val testSubscriber = TestSubscriber<List<Review>>()
        result.subscribe(testSubscriber)
        testSubscriber.assertComplete()
        testSubscriber.assertNoErrors()
        testSubscriber.assertValueCount(1)

        val listResult = testSubscriber.values()[0]
        Assert.assertEquals(listResult.size, 3)
        Assert.assertEquals(listResult[0].review_id, r1.review_id)
        Assert.assertEquals(listResult[1].review_id, r2.review_id)
        Assert.assertEquals(listResult[2].review_id, r3.review_id)
    }

    @Test
    fun getLocalReviewsShouldReturnLocalReviewList() {
    }

    @Test
    fun addAll() {
    }
}