package ru.voodster.weatherstation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.voodster.weatherstation.weatherapi.Weather
import ru.voodster.weatherstation.weatherapi.WeatherInteractor


/**
 * Getting weather from server
 * onSuccess returns Weather (Data class) {@link MeasureSpec}
 * onError returns Error (String) {
 */

class IndicationViewModel : ViewModel() {

    init {
        Log.d("INDICATION viewModel", this.toString())
    }
    private val weatherLiveData = MutableLiveData<Weather>()
    private val errorLiveData = MutableLiveData<String>()

    // LiveData -> BehaviourSubject

    private val weatherInteractor = App.instance!!.weatherInteractor


    val weather: LiveData<Weather>
        get() = weatherLiveData

    val error: LiveData<String>
        get() = errorLiveData

    /**
     * On get data
     * Обработчик получения погоды
     * если успешно до добавляем их в livedata<Weather>
     *     за кооторым уже следит view
     */
    fun onGetData() {
        weatherInteractor.getWeather( object : WeatherInteractor.GetWeatherCallback {
            override fun onSuccess(weather: Weather) {
                weatherLiveData.postValue(weather)
            }

            override fun onError(error: String) {
                errorLiveData.postValue(error)
            }
        })
    }

}