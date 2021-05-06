package ru.voodster.weatherstation.weatherapi

class WeatherData {

    private var curWeather: Weather? = null
    private val fakeWeather = Weather(0,0,0,0,0,0)

    private val lastWeather = ArrayList<Weather>() // TODO сделать получение списка последних показаний

    val cachedOrFakeWeather: Weather
        get() = curWeather ?: fakeWeather


    fun addToCache(weather: Weather) {
        this.curWeather = weather
    }
}