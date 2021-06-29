package ru.voodster.weatherstation.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Single

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather_table limit :limit offset :offset ")
    fun getWeather(offset:Int, limit:Int): Single<List<WeatherE>>

    @Insert
    fun insertAll(vararg users: WeatherE)
    @Delete
    fun delete(user: WeatherE)
}