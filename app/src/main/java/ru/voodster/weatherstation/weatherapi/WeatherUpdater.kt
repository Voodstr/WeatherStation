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
import ru.voodster.weatherstation.App

class WeatherUpdater(private val service: WeatherService): LifecycleObserver {

    private val handler = Handler(Looper.getMainLooper())


/*
    fun setLifecycle(lifecycle: Lifecycle) {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)){
            lifecycle.addObserver(App.instance!!.weatherUpdater)
        }
    }
*/

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onLifecycleResume() {
        Log.d(TAG, "onResume")

        handler.postDelayed(GetWeatherRunnable(), DELAY.toLong())
        handler.postDelayed(GetWeatherTableRunnable(), DELAY.toLong())
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onLifecyclePaused() {
        Log.d(TAG, "onPause")

        handler.removeCallbacksAndMessages(null)
    }

    inner class GetWeatherRunnable : Runnable {
        override fun run() {

            service.getData().enqueue(object : Callback<Weather> {
                override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                    handler.postDelayed(GetWeatherRunnable(), DELAY.toLong())
                }

                override fun onFailure(call: Call<Weather>, t: Throwable) {

                }
            })
        }
    }

    inner class GetWeatherTableRunnable : Runnable {
        override fun run() {

            service.getTableData().enqueue(object : Callback<List<Weather>> {
                override fun onResponse(call: Call<List<Weather>>, response: Response<List<Weather>>) {
                    handler.postDelayed(GetWeatherTableRunnable(), DELAY.toLong())
                }

                override fun onFailure(call: Call<List<Weather>>, t: Throwable) {

                }
            })
        }
    }

    companion object {
        private const val TAG = "WeatherUpdater"
        private const val DELAY = 5000
    }
}