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
import androidx.recyclerview.widget.RecyclerView
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
        recyclerView = requireView().findViewById(R.id.TableRV) // находим RecylerView  в layout XML
        adapter =  WeatherTableAdapter(LayoutInflater.from(context)) // Создаем адаптер для элементов списка
        recyclerView!!.adapter = adapter // передаем адаптер нашему RecyclerView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initRecycler() // инициализируем список
        tableVM.onGetTable() // запрашиваем данные при создании фрагмента
        tableVM.tableWeather.observe(viewLifecycleOwner,  { table ->   // Подписываемся на обновление данных
            Log.d("UI update table","$table")
            adapter?.setItems(table)}) // Передаем данные в нашу таблицу
        tableVM.error.observe(viewLifecycleOwner,  { error -> // Показываем ошибку если есть даненые об ошибке
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        })
    }


    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(weather: Weather) { // привязываем данные к нашей строчке в таблице
            itemView.findViewById<TextView>(R.id.tempTv).text = weather.temp.toDouble().div(10).toString().plus("°C")
            itemView.findViewById<TextView>(R.id.humTv).text = weather.hum.toString().plus("%")
            val sdf = SimpleDateFormat("HH:mm", Locale.ROOT)
            itemView.findViewById<TextView>(R.id.dateTv).text = sdf.format(Date(weather.date.toLong().times(1000)))
            itemView.findViewById<TextView>(R.id.pressTv).text = weather.press.toString()
        }
    }

    /**
     * Weather table adapter
     * Класс адаптера нашей таблицы
     * @property inflater
     * @constructor Create empty Weather table adapter
     */
    class WeatherTableAdapter(private val inflater: LayoutInflater) : RecyclerView.Adapter<WeatherViewHolder>() {
        private val items = ArrayList<Weather>()


        fun setItems(tableWeather: List<Weather>) {  // передаем чюда нашу таблицу и добавляем все данные
            Log.d("UI setItems", "$tableWeather")
            items.clear()
            items.addAll(tableWeather)

            notifyDataSetChanged() // уведомляем что надо перерисовать таблицу
        }

        /**
         *  view holder - держатель элемента по сути строка в таблице
         *  Ее вид описан в  R.layout.item_weather.xml
         *
         * @param parent
         * @param viewType
         * @return
         */
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
            return WeatherViewHolder(inflater.inflate(R.layout.item_weather, parent, false))
        }

        override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) { // привязываем строчку к позиции в таблице
            holder.bind(items[position])
        }

        override fun getItemCount(): Int { // Метод возвращает размер таблицы
            return items.size
        }




    }



}


