package ru.voodster.weatherstation.graphics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import ru.voodster.weatherstation.WeatherViewModel
import ru.voodster.weatherstation.databinding.FragmentGraphBinding
import ru.voodster.weatherstation.realm.WeatherObject

class GraphFragment : Fragment() {

    companion object{
        const val TAG = "GraphFragment"
        fun newInstance(): GraphFragment {
            return GraphFragment()
        }

    }

    private val viewModel: WeatherViewModel by activityViewModels()

    private var _binding : FragmentGraphBinding? =null
    private val binding get() = _binding!!

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
        viewModel.getChartWeather()
        viewModel.chartWeather.observe(viewLifecycleOwner){
            fillTempChart(it)
        }

    }


    fun fillTempChart(data:List<WeatherObject>){

    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}