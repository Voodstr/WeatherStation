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


    private val fakeWeather = WeatherModel(0,0,0,0,0,0)
    private var curWeather: WeatherModel = fakeWeather

    private val tableWeather = ArrayList<WeatherModel>()
    private val fakeTable  = arrayListOf(fakeWeather,fakeWeather,fakeWeather)

    fun getCurrentWeather(callback: GetWeatherCallback){
        api.getWeather()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( { r ->
                run {
                    curWeather = r
                    callback.onSuccess(curWeather)
                }
            },{ error ->
            callback.onError(error.localizedMessage)
        })
    }

    fun getTableWeather(callback: GetTableWeatherCallback){
        api.getTableData(20)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( { r ->
                run {
                    tableWeather.clear()
                    tableWeather.addAll(r)
                    callback.onSuccess(tableWeather)
                }
            },{ error ->
                callback.onError(error.localizedMessage)
            })
    }

    interface GetWeatherCallback{
        fun onSuccess(result: WeatherModel)
        fun onError(error:String?)
    }
    interface GetTableWeatherCallback{
        fun onSuccess(result: List<WeatherModel>)
        fun onError(error:String?)
    }
}