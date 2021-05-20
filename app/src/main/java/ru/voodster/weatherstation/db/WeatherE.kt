package ru.voodster.weatherstation.db

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class WeatherE(
    @PrimaryKey val wid: Int,
    @ColumnInfo(name = "HomeT") val HomeT: Int,
    @ColumnInfo(name = "Press") val Press: Int,
    @ColumnInfo(name = "Date") val Date: Long,
    @ColumnInfo(name = "Hum") val Hum: Int,
    @ColumnInfo(name = "Temp") val Temp: Int,
    @ColumnInfo(name = "Fav") val Fav: Boolean?
)
