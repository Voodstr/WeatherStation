package ru.voodster.weatherstation.weatherapi

class WeatherData {

    private var curWeather: Weather? = null
    private val fakeWeather = Weather(0,0,0,0,0,0)

    private val tableWeather = ArrayList<Weather>()
    private val fakeTable  = arrayListOf<Weather>(fakeWeather,fakeWeather,fakeWeather)


    val cachedOrFakeWeather: Weather
        get() = curWeather ?: fakeWeather


    val cachedOrFakeTable: List<Weather>
        get() = if (tableWeather.size > 0 )
            tableWeather
        else
            fakeTable

    fun addToCache(weather: Weather) {
        this.curWeather = weather
    }
    fun addToTable(weatherTable : List<Weather>){
        this.tableWeather.clear()
        this.tableWeather.addAll(weatherTable)
    }
}