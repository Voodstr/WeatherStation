package ru.voodster.weatherstation

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build

class App : Application() {


    companion object {
        var instance: App? = null
            private set

        const val CHANNEL_WATCH = "Watch Later"

        fun getPreferenceUrl():String?{
            return instance?.getSharedPreferences("root_preferences.xml", MODE_PRIVATE)?.getString("weatherURL","https://db.shs.com.ru/")
        }
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        createNotificationChannel()

    }


    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "WeatherStation"
            val descriptionText = "WeatherStation"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channelWatch = NotificationChannel(CHANNEL_WATCH, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channelWatch)
        }
    }
}
