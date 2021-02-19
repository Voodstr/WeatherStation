package ru.voodster.weatherstation.weatherapi

import retrofit2.Call
import retrofit2.http.*


interface CurrentWeatherRequest{

@GET("/tst/json.lsp")
fun getData():Call<WeatherDataClass>
}