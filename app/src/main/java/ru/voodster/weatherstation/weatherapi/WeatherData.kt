package ru.voodster.weatherstation.weatherapi

class WeatherData {

    private var curWeather: Weather? = null
    private val fakeWeather = Weather(0,0,0,0,0,0)

    private val tableWeather = ArrayList<Weather>()
    private val fakeTable  = arrayListOf<Weather>(fakeWeather,fakeWeather,fakeWeather)


    val cachedOrFakeWeather: Weather  // возвращаем либо действительные данные либо подменяем их на пустые
        get() = curWeather ?: fakeWeather // чтобы не возвращать null


    val cachedOrFakeTable: List<Weather> // тоже самое но для таблицы(списка)
        get() = if (tableWeather.size > 0 )
            tableWeather
        else
            fakeTable

    fun addToCache(weather: Weather) { //
        this.curWeather = weather
    }
    fun addToTable(weatherTable : List<Weather>){
        this.tableWeather.clear()
        this.tableWeather.addAll(weatherTable)
    }
}