package ru.voodster.weatherstation.weatherapi

import retrofit2.Call
import retrofit2.http.*

interface WeatherTableRequest {
    @GET("/tst/json.lsp?rows=20")
    fun getData():Call<Weather>
}