package ru.voodster.weatherstation.weatherapi

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Weather(
    var Date: Int,
    val HomeT: Int,
    val Hum: Int,
    val ID: Int,
    val Press: Int,
    val Temp: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(Date)
        parcel.writeInt(HomeT)
        parcel.writeInt(Hum)
        parcel.writeInt(ID)
        parcel.writeInt(Press)
        parcel.writeInt(Temp)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Weather> {
        override fun createFromParcel(parcel: Parcel): Weather {
            return Weather(parcel)
        }

        override fun newArray(size: Int): Array<Weather?> {
            return arrayOfNulls(size)
        }
    }
}