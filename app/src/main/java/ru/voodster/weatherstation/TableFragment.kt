package ru.voodster.weatherstation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import ru.voodster.weatherstation.databinding.FragmentIndicationBinding
import ru.voodster.weatherstation.databinding.FragmentTableBinding

import ru.voodster.weatherstation.weatherapi.Weather
import java.text.SimpleDateFormat
import java.util.*


class TableFragment : Fragment() {

    private var _binding : FragmentTableBinding? =null
    private val binding get() = _binding!!


    private val tableVM:TableViewModel by activityViewModels()
    private var recyclerView: RecyclerView? = null
    private var adapter : WeatherTableAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTableBinding.inflate(inflater, container, false)
        //binding.updateBtn.setOnClickListener {
       //     tableVM.onGetTable()
        //}
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initRecycler() {
        recyclerView = requireView().findViewById(R.id.TableRV)
        adapter =  WeatherTableAdapter(LayoutInflater.from(context))
        recyclerView!!.adapter = adapter
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initRecycler()
        tableVM.onGetTable()
        tableVM.tableWeather.observe(viewLifecycleOwner,  { table ->
            Log.d("UI update table","$table")
            adapter?.setItems(table)})
        tableVM.error.observe(viewLifecycleOwner,  { error ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        })
    }


    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(weather: Weather) {
            itemView.findViewById<TextView>(R.id.tempTv).text =
                "${weather.temp.toDouble().div(10).toString()} °C"
            itemView.findViewById<TextView>(R.id.humTv).text = "${weather.hum.toString()}%"
            val sdf = SimpleDateFormat("HH:mm", Locale.ROOT)
            itemView.findViewById<TextView>(R.id.dateTv).text = sdf.format(Date(weather.date.toLong().times(1000)))
            itemView.findViewById<TextView>(R.id.pressTv).text = weather.press.toString()
        }
    }

    class WeatherTableAdapter(private val inflater: LayoutInflater) : RecyclerView.Adapter<WeatherViewHolder>() {
        private val items = ArrayList<Weather>()


        fun setItems(tableWeather: List<Weather>) {
            Log.d("UI setItems", "${tableWeather}")
            items.clear()
            items.addAll(tableWeather)

            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
            return WeatherViewHolder(inflater.inflate(R.layout.item_weather, parent, false))
        }

        override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount(): Int {
            return items.size
        }




    }



}


