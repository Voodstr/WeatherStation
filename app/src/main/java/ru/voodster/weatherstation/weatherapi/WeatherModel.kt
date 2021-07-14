package ru.voodster.weatherstation.weatherapi

import com.google.gson.annotations.SerializedName


data class WeatherModel(

    @SerializedName("Date") var date: Int = 0,
    @SerializedName("HomeT") var homeT: Int = 0,
    @SerializedName("Hum") var hum: Int = 0,
    @SerializedName("ID") var iD: Int = 0,
    @SerializedName("Press") var press: Int = 0,
    @SerializedName("Temp") var temp: Int = 0
)