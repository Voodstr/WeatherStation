package ru.voodster.weatherstation.weatherapi

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName



class Weather{

    @SerializedName("Date")
    var date: Int = 0
    @SerializedName("HomeT")
    var homeT: Int = 0
    @SerializedName("Hum")
    var hum: Int = 0
    @SerializedName("ID")
    var iD: Int = 0
    @SerializedName("Press")
    var press: Int = 0
    @SerializedName("Temp")
    var temp: Int = 0

    constructor() {}

    constructor(date:Int, homeT:Int, hum:Int, id:Int, press:Int, temp:Int) {
        this.date = date
        this.homeT = homeT
        this.hum = hum
        this.iD = id
        this.press = press
        this.temp = temp
    }

}