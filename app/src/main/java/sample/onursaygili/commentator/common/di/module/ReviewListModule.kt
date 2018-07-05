package sample.onursaygili.commentator.common.di.module

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import sample.onursaygili.commentator.common.di.ViewModelKey
import sample.onursaygili.commentator.reviews.view.list.ReviewListFragment
import sample.onursaygili.commentator.reviews.view.list.ReviewListViewModel

@Module
internal abstract class ReviewListModule {

    @Binds
    @IntoMap
    @ViewModelKey(ReviewListViewModel::class)
    abstract fun bindReviewListViewModel(viewModel: ReviewListViewModel): ViewModel

    @ContributesAndroidInjector
    abstract fun contributeReviewListFragment(): ReviewListFragment

}