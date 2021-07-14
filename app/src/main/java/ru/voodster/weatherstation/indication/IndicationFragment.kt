package ru.voodster.weatherstation.indication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ru.voodster.weatherstation.R
import ru.voodster.weatherstation.WeatherViewModel
import ru.voodster.weatherstation.databinding.FragmentIndicationBinding
import ru.voodster.weatherstation.weatherapi.WeatherModel
import java.text.SimpleDateFormat
import java.util.*

class IndicationFragment : Fragment() {

    companion object{
        const val TAG = "IndicationFragment"
        fun newInstance(): IndicationFragment {
            return IndicationFragment()
        }

    }
    private var _binding: FragmentIndicationBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel : WeatherViewModel by activityViewModels()

    private var weatherNum = WeatherModel(0,0,0,0,0,0)



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.swipeUpdate.setOnRefreshListener {
            viewModel.getCurrentWeather()
            binding.swipeUpdate.isRefreshing = false
        }
        viewModel.getCurrentWeather()
        viewModel.currentWeather.observe(viewLifecycleOwner, { weather ->
            setViews(weather)
        })
        viewModel.errorMsg.observe(viewLifecycleOwner, { error -> Toast.makeText(context, error, Toast.LENGTH_SHORT).show()})
    }


    private fun setViews(weatherModel: WeatherModel){
        weatherNum = weatherModel
        val sdf = SimpleDateFormat("dd/MM HH:mm", Locale.ROOT)
        binding.curHumTV.text = weatherNum.hum.toString().plus("%")
        binding.curTempTV.text = (weatherNum.temp.toDouble().div(10.0)).toString().plus("°C")
        binding.curPressTV.text = weatherNum.press.toString().plus(" мм.")
        binding.curDateTv.text = sdf.format(Date(weatherNum.date.toLong().times(1000)))
        setTempColor()
        setBackground()
    }

    private fun setTempColor(){
        if (weatherNum.temp >= 0) {
            binding.curTempTV.setTextColor(resources.getColor(R.color.tempHot, context?.applicationContext?.theme))
        } else binding.curTempTV.setTextColor(resources.getColor(R.color.tempCold, context?.applicationContext?.theme))
    }

    fun setBackground(){
        when (Calendar.getInstance().get(Calendar.MONTH).plus(1)){
            12,1,2 -> binding.indicationLayout.setBackgroundResource(R.drawable.winter)
            3,4,5 -> binding.indicationLayout.setBackgroundResource(R.drawable.spring)
            6,7,8 -> binding.indicationLayout.setBackgroundResource(R.drawable.summer)
            9,10,11 -> binding.indicationLayout.setBackgroundResource(R.drawable.autumn)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIndicationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}



