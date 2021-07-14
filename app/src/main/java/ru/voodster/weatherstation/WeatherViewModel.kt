package ru.voodster.weatherstation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.Component
import ru.voodster.weatherstation.db.DbModule
import ru.voodster.weatherstation.weatherapi.ApiModule
import ru.voodster.weatherstation.weatherapi.WeatherModel
import javax.inject.Singleton



class WeatherViewModel : ViewModel() {
    private val viewModelComponent=DaggerViewModelComponent.builder().build()
    private val weatherRepository = viewModelComponent.repos()

    init {
        Log.d("Weather viewModel", this.toString())
    }

    private val currentWeatherLiveData = MutableLiveData<WeatherModel>()
    val currentWeather : LiveData<WeatherModel>
        get() = currentWeatherLiveData


    private val tableWeatherLiveData = MutableLiveData<List<WeatherModel>>()
    val tableWeather : LiveData<List<WeatherModel>>
        get() = tableWeatherLiveData

    val errorMsg = SingleLiveEvent<String>()

    fun getCurrentWeather(){
        weatherRepository.getCurrentWeather(object: WeatherRepository.GetWeatherCallback{
            override fun onError(error: String?) {
                errorMsg.postValue(error?:"Unknown Error")
            }

            override fun onSuccess(result: WeatherModel) {
                currentWeatherLiveData.postValue(result)
            }
        })
    }


    fun getTableWeather(){
        weatherRepository.getTableWeather(object: WeatherRepository.GetTableWeatherCallback{
            override fun onSuccess(result: List<WeatherModel>) {
                tableWeatherLiveData.postValue(result)
            }

            override fun onError(error: String?) {
                errorMsg.postValue(error?:"Unknown Error")
            }

        })
    }


}

@Component(modules = [ApiModule::class, DbModule::class])
@Singleton
interface ViewModelComponent {

    fun repos(): WeatherRepository

}
