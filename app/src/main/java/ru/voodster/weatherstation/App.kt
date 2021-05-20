package ru.voodster.weatherstation

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.voodster.weatherstation.db.WeatherDatabase
import ru.voodster.weatherstation.weatherapi.WeatherData
import ru.voodster.weatherstation.weatherapi.WeatherInteractor
import ru.voodster.weatherstation.weatherapi.WeatherService
import ru.voodster.weatherstation.weatherapi.WeatherUpdater

class App : Application() {

    lateinit var weatherService: WeatherService
    lateinit var weatherUpdater: WeatherUpdater
    lateinit var weatherInteractor: WeatherInteractor
    private var weatherData = WeatherData()
    val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            WeatherDatabase::class.java,"wthr"
        ).build()
    }

    override fun onCreate() {
        super.onCreate()

        instance = this


        initRetrofit()
        initInteractor()
    }

    private fun initInteractor() {
        weatherInteractor = WeatherInteractor(weatherService, weatherData)
    }

    private fun initRetrofit() {

        val okHttpClient = OkHttpClient.Builder()
                .build()

        weatherService = Retrofit.Builder()
                .baseUrl("https://db.shs.com.ru/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
                .create(WeatherService::class.java)

        weatherUpdater = WeatherUpdater(weatherService)
    }

    companion object {
        var instance: App? = null
            private set
    }
}
