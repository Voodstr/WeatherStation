package ru.voodster.weatherstation.weatherapi

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class WeatherUpdater(): LifecycleObserver {

    private val handler = Handler(Looper.getMainLooper())



    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onLifecycleResume() {
        Log.d(TAG, "onResume")

    }
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onLifecyclePaused() {
        Log.d(TAG, "onPause")

        handler.removeCallbacksAndMessages(null)
    }


    companion object {
        private const val TAG = "WeatherUpdater"
        private const val DELAY = 5000
    }
}