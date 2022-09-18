package Model

import android.os.Parcel
import android.os.Parcelable


data class User(
    var thename: String?,
    var type : String?,
    var umr: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }
    var imageUri: String = ""



    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(thename)
        parcel.writeString(type)
        parcel.writeString(umr)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
