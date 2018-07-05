package sample.onursaygili.commentator.reviews.view.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import sample.onursaygili.commentator.common.util.AbsentLiveData
import sample.onursaygili.commentator.common.util.ext.switchMap
import sample.onursaygili.commentator.reviews.model.Review
import sample.onursaygili.commentator.reviews.model.remote.ReviewQueryParams
import sample.onursaygili.commentator.reviews.model.repo.ReviewRepository
import javax.inject.Inject

class ReviewListViewModel @Inject constructor(private val repository: ReviewRepository) : ViewModel() {

    val params = MutableLiveData<ReviewQueryParams>()

    val reviews: LiveData<List<Review>>

    init {

        reviews = params.switchMap { queryParams ->
            when (queryParams) {
                null -> AbsentLiveData.create()
                else -> repository.getLocalReviews(queryParams)
                        .also {
                            updateReviewList(queryParams)
                        }
            }
        } ?: AbsentLiveData.create()


    }

    private fun updateReviewList(reviewQueryParams: ReviewQueryParams) {
        repository.getRemoteReviews(reviewQueryParams)
                .map {
                    repository.addAll(it)
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(Flowable.empty())
                .subscribe()
    }
}
