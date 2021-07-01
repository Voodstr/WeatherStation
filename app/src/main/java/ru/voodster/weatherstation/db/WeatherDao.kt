package ru.voodster.weatherstation.db

import androidx.room.*
import io.reactivex.rxjava3.core.Single

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather_table limit :limit offset :offset ")
    fun getWeather(offset:Int, limit:Int): Single<List<WeatherE>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertAll(vararg users: WeatherE)
    @Delete
    fun delete(user: WeatherE)
}