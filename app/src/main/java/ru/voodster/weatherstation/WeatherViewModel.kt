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



}

@Component(modules = [ApiModule::class, DbModule::class])
@Singleton
interface ViewModelComponent {

    fun repos(): WeatherRepository

}
