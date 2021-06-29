package ru.voodster.weatherstation.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "weather_table")
@Parcelize
data class WeatherE(
    @PrimaryKey val wid: Int,
    @ColumnInfo(name = "HomeT") val HomeT: Int,
    @ColumnInfo(name = "Press") val Press: Int,
    @ColumnInfo(name = "Date") val Date: Long,
    @ColumnInfo(name = "Hum") val Hum: Int,
    @ColumnInfo(name = "Temp") val Temp: Int
):Parcelable
