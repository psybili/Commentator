package sample.onursaygili.commentator.reviews.model.remote

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class ReviewQueryParams(
        var count: Int,
        var page: Int,
        var rating: Int,
        var sortBy: String,
        var direction: String
) : Parcelable, Serializable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(count)
        parcel.writeInt(page)
        parcel.writeInt(rating)
        parcel.writeString(sortBy)
        parcel.writeString(direction)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReviewQueryParams> {
        override fun createFromParcel(parcel: Parcel): ReviewQueryParams {
            return ReviewQueryParams(parcel)
        }

        override fun newArray(size: Int): Array<ReviewQueryParams?> {
            return arrayOfNulls(size)
        }
    }
}