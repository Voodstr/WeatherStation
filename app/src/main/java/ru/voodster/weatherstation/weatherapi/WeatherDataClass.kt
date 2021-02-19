package ru.voodster.weatherstation.weatherapi

data class WeatherDataClass(
    val Date: Int,
    val HomeT: Int,
    val Hum: Int,
    val ID: Int,
    val Press: Int,
    val Temp: Int
)