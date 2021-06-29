package ru.voodster.weatherstation.weatherapi

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.voodster.weatherstation.App
import javax.inject.Singleton


@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideApi(): WeatherService {

        val url:String = App.getPreferenceUrl()?: "https://db.shs.com.ru/"
        val okHttpClient = OkHttpClient.Builder()
            .build()

        val rxAdapter = RxJava3CallAdapterFactory.create()

        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addCallAdapterFactory(rxAdapter)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(WeatherService::class.java)
    }
}