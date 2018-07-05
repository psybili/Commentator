package sample.onursaygili.commentator.common.di

import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import sample.onursaygili.commentator.common.di.module.ReviewListModule
import sample.onursaygili.commentator.reviews.view.list.ReviewListActivity

@Module
internal abstract class UiModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector(modules = [ReviewListModule::class])
    internal abstract fun contributeListActivity(): ReviewListActivity

}