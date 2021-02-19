package ru.voodster.weatherstation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.voodster.weatherstation.R
import ru.voodster.weatherstation.databinding.ActivityMainBinding


class TableFragment : Fragment() {

    private var _binding : ActivityMainBinding? =null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_table, container, false)
    }

    override fun onDestroyView() {
        //binding = null
        super.onDestroyView()
    }
}