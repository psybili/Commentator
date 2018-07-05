package sample.onursaygili.commentator.common.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import sample.onursaygili.commentator.reviews.model.Review
import sample.onursaygili.commentator.reviews.model.local.ReviewDao

@Database(entities = [(Review::class)], version = 1, exportSchema = false)
abstract class ReviewDatabase : RoomDatabase() {
    abstract fun reviewDao(): ReviewDao
}
