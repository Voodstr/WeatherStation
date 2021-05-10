package ru.voodster.weatherstation.weatherapi

import retrofit2.Call
import retrofit2.http.*


interface WeatherService{

    @GET("/tst/json.lsp")
    fun getData():Call<Weather>


    @GET("/tst/json.lsp?rows=20")
    fun getTableData():Call<List<Weather>>
}
