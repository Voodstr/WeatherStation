package ru.voodster.weatherstation.weatherapi

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService{

    @GET("tst/json.lsp")
    fun getWeather(): Single<WeatherModel>


    @GET("/tst/json.lsp")
    fun getTableData(@Query("rows") rows:Int):Single<List<WeatherModel>>
}
