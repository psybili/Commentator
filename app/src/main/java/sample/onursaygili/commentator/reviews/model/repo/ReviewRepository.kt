package sample.onursaygili.commentator.reviews.model.repo

import android.arch.lifecycle.LiveData
import io.reactivex.Flowable
import sample.onursaygili.commentator.reviews.model.Review
import sample.onursaygili.commentator.reviews.model.local.ReviewDao
import sample.onursaygili.commentator.reviews.model.remote.ReviewQueryParams
import sample.onursaygili.commentator.reviews.model.remote.ReviewService
import javax.inject.Inject

class ReviewRepository @Inject constructor(
        private val reviewService: ReviewService,
        private val reviewDao: ReviewDao
) {

    fun getRemoteReviews(reviewQueryParams: ReviewQueryParams)
            : Flowable<List<Review>> {
        return reviewService.listReviews(
                reviewQueryParams.count,
                reviewQueryParams.page,
                reviewQueryParams.rating,
                reviewQueryParams.sortBy,
                reviewQueryParams.direction
        )
                .flatMap {
                    Flowable.just(it.data.toList())
                }
    }

    fun getLocalReviews(reviewQueryParams: ReviewQueryParams)
            : LiveData<List<Review>> {
        return reviewDao.getReviewList(
//                reviewQueryParams.count,
//                reviewQueryParams.page,
//                reviewQueryParams.rating,
//                reviewQueryParams.sortBy,
//                reviewQueryParams.direction
        )
    }

    fun addAll(reviewList: List<Review>) {
        reviewDao.addAll(reviewList)
    }

}