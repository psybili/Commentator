package sample.onursaygili.commentator.reviews.model.remote

import android.os.Parcel
import android.os.Parcelable
import sample.onursaygili.commentator.reviews.model.Review

data class ReviewResponse(
        val isStatus: Boolean,
        val total_reviews_comments: Int,
        val data: List<Review>
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readByte() != 0.toByte(),
            parcel.readInt(),
            parcel.createTypedArrayList(Review))

    override fun toString(): String {
        return "isStatus: $isStatus,\ntotal reviews comments: $total_reviews_comments,\ndata size: ${data.size}"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (isStatus) 1 else 0)
        parcel.writeInt(total_reviews_comments)
        parcel.writeTypedList(data)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReviewResponse> {
        override fun createFromParcel(parcel: Parcel): ReviewResponse {
            return ReviewResponse(parcel)
        }

        override fun newArray(size: Int): Array<ReviewResponse?> {
            return arrayOfNulls(size)
        }
    }
}