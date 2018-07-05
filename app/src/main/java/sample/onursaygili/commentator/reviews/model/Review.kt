package sample.onursaygili.commentator.reviews.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.databinding.BaseObservable
import android.os.Parcel
import android.os.Parcelable
import java.util.*

@Entity
data class Review(
        @PrimaryKey
        val review_id: Long,
        val rating: String,
        val message: String,
        val author: String,
        val foreignLanguage: Boolean,
        val date: String,
        val languageCode: String,
        val reviewerName: String,
        val reviewerCountry: String
) : BaseObservable(), Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(review_id)
        parcel.writeString(rating)
        parcel.writeString(message)
        parcel.writeString(author)
        parcel.writeByte(if (foreignLanguage) 1 else 0)
        parcel.writeString(date)
        parcel.writeString(languageCode)
        parcel.writeString(reviewerName)
        parcel.writeString(reviewerCountry)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Review> {
        override fun createFromParcel(parcel: Parcel): Review {
            return Review(parcel)
        }

        override fun newArray(size: Int): Array<Review?> {
            return arrayOfNulls(size)
        }

        fun randomReview() = Review(
                Random().nextLong(),
                randomString(),
                randomString(),
                randomString(),
                Random().nextBoolean(),
                randomString(),
                randomString(),
                randomString(),
                randomString()
        )

        private fun randomString(): String {
            return " ${System.currentTimeMillis()}"
        }

        private fun randomLong(): Long {
            return Random().nextLong()
        }
    }
}