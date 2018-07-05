package sample.onursaygili.commentator.common.model

import android.arch.persistence.room.*
import android.databinding.BaseObservable

@Dao
interface BaseDao<T : BaseObservable> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(t: T)

    @Update
    fun update(t: T)

    @Delete
    fun delete(t: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAll(l: List<T>)
}