package ru.voodster.weatherstation

import android.graphics.Color
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*

import ru.voodster.weatherstation.databinding.FragmentIndicationBinding
import ru.voodster.weatherstation.weatherapi.Weather
import ru.voodster.weatherstation.weatherapi.WeatherData
import java.text.SimpleDateFormat
import java.util.*

import kotlin.collections.ArrayList

class IndicationFragment : Fragment() {


    private var _binding: FragmentIndicationBinding? = null
    private val binding get() = _binding!!

    private val viewModel : WeatherViewModel by viewModels()

    private var weatherNum = Weather(0,0,0,0,0,0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setWeather(weatherNum)

        viewModel.weather.observe(viewLifecycleOwner, { weather -> setWeather(weather) })
        viewModel.error.observe(viewLifecycleOwner, {error -> Toast.makeText(context, error, Toast.LENGTH_SHORT).show()})

    }

    fun bind(){

    }

    fun setWeather(weather: Weather){
        weatherNum = weather
        val sdf = SimpleDateFormat("dd/MM HH:mm", Locale.ROOT)
        binding.humView.text = weatherNum.Hum.toString()+"%"
        binding.tempView.text = (weatherNum.Temp.toDouble().div(10.0)).toString() +" C"
        binding.pressView.text = weatherNum.Press.toString()
        binding.dateView.text = sdf.format(Date(weatherNum.Date.toLong().times(1000)))
        if (weatherNum.Temp >= 0) {
            binding.tempView.setTextColor(Color.RED)
        } else binding.tempView.setTextColor(Color.BLUE)

    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_indication, container, false)

        _binding = FragmentIndicationBinding.inflate(inflater, container, false)
        binding.refreshBtn.setOnClickListener {
            viewModel.onGetDataClick()
        }
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }



    /* fun setInfo (result: String) {
            Log.i("JSON RESPONSE RESULT", result)

            try {
                val jsonObj = JSONObject(result)

                sPressure = jsonObj.optInt("Press")
                sTemperature = jsonObj.optInt("Temp")
                sDate = jsonObj.optLong("Date")
                sHumidity = jsonObj.optInt("Hum")
            } catch (e: Exception) {
                Log.i("JSON PROBLEM", e.message.toString())
            //    Toast.makeText(this@FragmentI, "Certificate is needed", Toast.LENGTH_SHORT).show()
            }
            setValues()
        }*/


    /*private suspend fun setValues() = withContext(Dispatchers.Main) {
            //val sdf = SimpleDateFormat("dd/MM HH:mm", Locale.ROOT)
        val url = "https://db.shs.com.ru/tst/json.lsp"
        val result = withContext(Dispatchers.IO){
           val wDataType= object : TypeToken<ArrayList<WeatherModel>>() {}.type
            weatherData = Gson().fromJson(httpGet(url),wDataType)
        }
            Log.i("CURRENT DATA: ",weatherData[0].toString())
            binding.humView.text = (weatherData[0].Hum ?: 0).toString()
            binding.tempView.text = (weatherData[0].Temp?.div(10) ?: 0).toString()
            binding.pressView.text = (weatherData[0].Press ?: 0).toString()
          //  binding.dateView.text = sdf.format(Date((weatherData[0].Date ?: 0).times(100) as Long)
            if (weatherData[0].Temp!! >= 0) {
                binding.tempView.setTextColor(Color.RED)
            } else binding.tempView.setTextColor(Color.BLUE)
        } */

}



