package de.foodbro.app.util

import android.os.Parcel
import android.os.Parcelable

data class LongArg(val arg: Long) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readLong())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(arg)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LongArg> {
        override fun createFromParcel(parcel: Parcel): LongArg {
            return LongArg(parcel)
        }

        override fun newArray(size: Int): Array<LongArg?> {
            return arrayOfNulls(size)
        }
    }
}