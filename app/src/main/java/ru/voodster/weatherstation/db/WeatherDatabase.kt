package ru.voodster.weatherstation.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(WeatherE::class),version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}