package sample.onursaygili.commentator.reviews.model.local

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import sample.onursaygili.commentator.common.model.BaseDao
import sample.onursaygili.commentator.reviews.model.Review

@Dao
interface ReviewDao : BaseDao<Review> {

    @Query("SELECT * FROM Review")
    fun getReviewList(
//            count: Int,
//            page: Int,
//            rating: Int,
//            sortBy: String,
//            direction: String
    ): LiveData<List<Review>>

    @Query("SELECT * FROM Review WHERE review_id = :review_id")
    fun getReviewById(review_id: Long): LiveData<Review>

}