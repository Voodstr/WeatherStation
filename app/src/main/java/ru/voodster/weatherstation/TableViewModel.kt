package ru.voodster.weatherstation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.voodster.weatherstation.weatherapi.Weather
import ru.voodster.weatherstation.weatherapi.WeatherInteractor

class TableViewModel:ViewModel() {
    init {
        Log.d("Table viewModel", this.toString())
    }
    private val tableWeatherLiveData = MutableLiveData<List<Weather>>()
    private val errorLiveData = MutableLiveData<String>()

    // LiveData -> BehaviourSubject

    private val weatherInteractor = App.instance!!.weatherInteractor

    val tableWeather: LiveData<List<Weather>>
        get() = tableWeatherLiveData

    val error: LiveData<String>
        get() = errorLiveData


    fun onGetTable() {
        weatherInteractor.getWeatherTable( object : WeatherInteractor.GetWeatherTableCallBack {
            override fun onSuccess(tableWeather: List<Weather>) {
                Log.d("TABLE onGetTable", "success")
                tableWeatherLiveData.postValue(tableWeather)
            }

            override fun onError(error: String) {
                errorLiveData.postValue(error)
                Log.d("TABLE onGetTable", "weatherERROR")
            }
        })
    }
}