package ru.voodster.weatherstation.weatherapi

import retrofit2.Call
import retrofit2.http.*


interface WeatherService{

@GET("tst/json.lsp")
fun getWeather():Call<Weather>
}