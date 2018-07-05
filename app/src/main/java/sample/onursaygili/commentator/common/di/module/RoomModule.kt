package sample.onursaygili.commentator.common.di.module

import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import sample.onursaygili.commentator.App
import sample.onursaygili.commentator.common.model.ReviewDatabase
import sample.onursaygili.commentator.reviews.model.local.ReviewDao
import sample.onursaygili.commentator.reviews.model.remote.ReviewService
import sample.onursaygili.commentator.reviews.model.repo.ReviewRepository
import javax.inject.Singleton

@Module(includes = [DataModule::class])
class RoomModule(app: App) {

    private val database: ReviewDatabase = Room.databaseBuilder(
            app,
            ReviewDatabase::class.java,
            "Review.db"
    ).build()

    @Provides
    @Singleton
    fun provideReviewRepository(
            reviewDao: ReviewDao,
            reviewService: ReviewService
    ): ReviewRepository {
        return ReviewRepository(reviewService, reviewDao)
    }

    @Provides
    @Singleton
    fun provideReviewDao(database: ReviewDatabase): ReviewDao {
        return database.reviewDao()
    }

    @Provides
    @Singleton
    fun provideReviewDatabase(): ReviewDatabase {
        return database
    }
}
