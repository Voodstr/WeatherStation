package ru.voodster.weatherstation.realm

import io.realm.RealmObject

data class WeatherObject(
    val HomeT: Int,
    val Press: Int,
    val Date: Int,
    val Hum: Int,
    val Temp: Int
) : RealmObject()