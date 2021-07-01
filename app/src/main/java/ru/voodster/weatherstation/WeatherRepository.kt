package ru.voodster.weatherstation

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.realm.Realm
import ru.voodster.weatherstation.db.WeatherDatabase
import ru.voodster.weatherstation.realm.WeatherObject
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
    private val chartWeather = ArrayList<WeatherObject>()
    private val fakeTable  = arrayListOf(fakeWeather,fakeWeather,fakeWeather)

    val realmDb = Realm.getDefaultInstance()

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


    fun getChartWeather(callback: GetChartWeatherCallback){
        api.getTableData(20)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( { r ->
                run {
                    chartWeather.clear()
                    chartWeather.addAll(WeatherMapper().map(r))
                    realmDb.beginTransaction()
                    realmDb.copyFromRealm(chartWeather)
                    realmDb.commitTransaction()
                    callback.onSuccess(chartWeather)
                }
            },{ error ->
                callback.onError(error.localizedMessage)
            })
    }

    inner class WeatherMapper: BaseMapper<WeatherObject,WeatherModel>(){
        override fun reverseMap(realmObject: WeatherObject?): WeatherModel? {
            return if (realmObject != null) {
                (WeatherModel(
                    realmObject.Date,
                    realmObject.HomeT,
                    realmObject.Hum,
                    realmObject.Press,
                    realmObject.Temp
                ))
            } else null
        }

        override fun map(model: WeatherModel?): WeatherObject? {
            return if (model != null) {
                (WeatherObject(
                    model.homeT,
                    model.press,
                    model.date,
                    model.hum,
                    model.temp,
                ))
            } else null
        }

    }

    interface GetWeatherCallback{
        fun onSuccess(result: WeatherModel)
        fun onError(error:String?)
    }
    interface GetTableWeatherCallback{
        fun onSuccess(result: List<WeatherModel>)
        fun onError(error:String?)
    }
    interface GetChartWeatherCallback{
        fun onSuccess(result: List<WeatherObject>)
        fun onError(error:String?)
    }
}