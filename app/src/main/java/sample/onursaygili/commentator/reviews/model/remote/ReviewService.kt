package sample.onursaygili.commentator.reviews.model.remote

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ReviewService {

    // this was mean :) shame on 403 errors with unclear messages
    @Headers(
            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
            "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:60.0) Gecko/20100101 Firefox/60.0"
    )
    @GET("/berlin-l17/tempelhof-2-hour-airport-history-tour-berlin-airlift-more-t23776/reviews.json")
    fun listReviews(
            @Query("count") count: Int,
            @Query("page") page: Int,
            @Query("rating") rating: Int,
            @Query("sortBy") sortBy: String,
            @Query("direction") direction: String
    ): Flowable<ReviewResponse>

}