package de.foodbro.app.util

import android.os.Parcel
import android.os.Parcelable

data class IntArg(val arg: Int) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(arg)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IntArg> {
        override fun createFromParcel(parcel: Parcel): IntArg {
            return IntArg(parcel)
        }

        override fun newArray(size: Int): Array<IntArg?> {
            return arrayOfNulls(size)
        }
    }
}