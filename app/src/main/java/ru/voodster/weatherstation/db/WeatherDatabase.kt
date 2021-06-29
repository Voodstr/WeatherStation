package ru.voodster.weatherstation.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WeatherE::class],version = 2)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}