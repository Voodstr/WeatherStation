package ru.voodster.weatherstation.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeatherDao {
    @Query(
        "SELECT * " +
             "FROM wthr")
    fun getAll(): List<WeatherE>
    @Query(
        "SELECT * " +
            "FROM wthr " +
            "WHERE Fav " +
            "Like true"
    )
    fun getFav(): List<WeatherE>

    @Query(
        "UPDATE whtr " +
            "SET Fav = true " +
            "WHERE wid = :id ")
    fun fav(id : Int)

    @Query(
        "UPDATE whtr " +
            "SET Fav = false " +
            "WHERE wid = :id "
    )
    fun unFav(id : Int)

    @Insert
    fun insertAll(vararg users: WeatherE)
    @Delete
    fun delete(user: WeatherE)
}