package ru.voodster.weatherstation.weatherapi

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class WeatherInteractor(private val weatherService: WeatherService, private val weatherData: WeatherData) {


    /**
     * Get weather
     * Асинхронное получение данных.
     * enqueue - ставит в очередь и уведомляет обьект о выполнении
     * onResponse -  успешное соединение ответ приходит в указанном виде (Weather)
     * onFailure - ошибка
     * @param callback
     */
    fun getWeather( callback: GetWeatherCallback) {

        weatherService.getData().enqueue(object : Callback<Weather> {
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                if (response.isSuccessful) {
                    weatherData.addToCache(response.body()!!)
                    callback.onSuccess(weatherData.cachedOrFakeWeather)
                } else {
                    callback.onError(response.code().toString() + "")
                }
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                callback.onError("Network error probably...")
            }
        })
    }

    /**
     * Get weather table
     * Тоже самое только для таблицы значений
     * @param callback
     */
    fun getWeatherTable(callback: GetWeatherTableCallBack) {

        weatherService.getTableData().enqueue(object : Callback<List<Weather>> {
            override fun onResponse(call: Call<List<Weather>>, response: Response<List<Weather>>) {
                if (response.isSuccessful) {
                    weatherData.addToTable(response.body()!!)

                    callback.onSuccess(weatherData.cachedOrFakeTable)
                } else {
                    callback.onError(response.code().toString() + "")
                }
            }

            override fun onFailure(call: Call<List<Weather>>, t: Throwable) {
                callback.onError("Network error probably...")
            }
        })
    }

    interface GetWeatherCallback {
        fun onSuccess(weather: Weather)
        fun onError(error: String)
    }

    interface GetWeatherTableCallBack {
        fun onSuccess(tableWeather: List<Weather>)
        fun onError(error: String)
    }
}