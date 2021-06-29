package ru.voodster.weatherstation

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.voodster.weatherstation.db.WeatherDatabase
import ru.voodster.weatherstation.weatherapi.WeatherModel
import ru.voodster.weatherstation.weatherapi.WeatherService
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val db: WeatherDatabase,
    private val api: WeatherService
) {

    private var curWeather: WeatherModel? = null
    private val fakeWeather = WeatherModel(0,0,0,0,0,0)

    private val tableWeather = ArrayList<WeatherModel>()
    private val fakeTable  = arrayListOf(fakeWeather,fakeWeather,fakeWeather)

    fun getCurrentWeather(callback: GetWeatherCallback){
        api.getWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( { r ->
                run {
                    callback.onSuccess(r)
                }
            },{ error ->
            callback.onError(error.localizedMessage)
        })
    }

    interface GetWeatherCallback{
        fun onSuccess(result: WeatherModel)
        fun onError(error:String?)
    }
}