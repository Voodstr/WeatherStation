package ru.voodster.weatherstation.weatherapi

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class WeatherInteractor(private val weatherService: WeatherService, private val weatherData: WeatherData) {



    fun getWeather( callback: GetWeatherCallback) {

        weatherService.getWeather().enqueue(object : Callback<Weather> {
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                if (response.isSuccessful) {
                    weatherData.addToCache(response.body()!!)
                    Log.d("shs response",response.code().toString() + "")
                    callback.onSuccess(weatherData.cachedOrFakeWeather)
                } else {
                    callback.onError(response.code().toString() + "")
                    Log.d("shs response",response.code().toString() + "")
                }
            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                callback.onError("Network error probably...")
            }
        })
    }

    interface GetWeatherCallback {
        fun onSuccess(weather: Weather)
        fun onError(error: String)
    }
}