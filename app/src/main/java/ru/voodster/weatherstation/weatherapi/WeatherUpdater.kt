package ru.voodster.weatherstation.weatherapi

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherUpdater(private val service: WeatherService): LifecycleObserver {

    private val handler = Handler(Looper.getMainLooper())



    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onLifecycleResume() {
        Log.d(TAG, "onResume")

        handler.postDelayed(GetWeatherRunnable(), DELAY.toLong())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onLifecyclePaused() {
        Log.d(TAG, "onPause")

        handler.removeCallbacksAndMessages(null)
    }

    inner class GetWeatherRunnable : Runnable {
        override fun run() {
            service.getWeather().enqueue(object : Callback<Weather> {
                override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                    handler.postDelayed(GetWeatherRunnable(), DELAY.toLong())
                }

                override fun onFailure(call: Call<Weather>, t: Throwable) {

                }
            })
        }
    }

    companion object {
        private const val TAG = "WeatherUpdater"
        private const val DELAY = 5000
    }
}