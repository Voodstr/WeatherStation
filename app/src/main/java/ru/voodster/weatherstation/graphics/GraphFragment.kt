package ru.voodster.weatherstation.graphics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import ru.voodster.weatherstation.R
import ru.voodster.weatherstation.WeatherViewModel
import ru.voodster.weatherstation.databinding.FragmentGraphBinding
import ru.voodster.weatherstation.weatherapi.WeatherModel

class GraphFragment : Fragment() {

    companion object{
        const val TAG = "GraphFragment"
        fun newInstance(): GraphFragment {
            return GraphFragment()
        }
    }

    private val viewModel : WeatherViewModel by activityViewModels()

    private var _binding : FragmentGraphBinding? =null
    private val binding get() = _binding!!


    val chartData = ArrayList<WeatherModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGraphBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTableWeather()
        viewModel.tableWeather.observe(viewLifecycleOwner){
            chartData.clear()
            chartData.addAll(it)
        }
        binding.startHour.maxValue = 24
        binding.endHour.maxValue = 24
        binding.chartLoad.setOnClickListener {
            if (binding.startHour.value>0){
                fillTempChart(chartData,binding.endHour.value,binding.startHour.value)
            }else fillTempChart(chartData,binding.endHour.value)
            binding.lineChart.invalidate()
        }
    }


    private fun fillTempChart(data:List<WeatherModel>, hours:Int){
        val entries = ArrayList<Entry>()
        val start = data.first().date-(60*60*hours)
        data.reversed().forEach {
            val timeInSec = it.date - start
            if (timeInSec>0){
                entries.add(Entry(timeInSec.toFloat().div(3600.00.toFloat()),it.temp.toFloat().div(10)))
            }

        }
        val dataSet = LineDataSet(entries,"temp")
        dataSet.setColor(R.color.colorPrimary)
        val lineData = LineData(dataSet)
        binding.lineChart.data = lineData
        binding.lineChart.notifyDataSetChanged()
    }

    private fun fillTempChart(data:List<WeatherModel>, startHours:Int, endHour:Int){
        val entries = ArrayList<Entry>()
        val start = data.first().date-(60*60*(24-startHours))
        data.reversed().forEach {
            when (val timeInSec = it.date - start){
               in  0..(24-endHour).times(3600) -> entries.add(Entry(timeInSec.toFloat().div(3600.00.toFloat()),it.temp.toFloat().div(10)))
            }

        }
        val dataSet = LineDataSet(entries,"temp")
        dataSet.setColor(R.color.colorPrimary)
        val lineData = LineData(dataSet)
        binding.lineChart.data = lineData
        binding.lineChart.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}