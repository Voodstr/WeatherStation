package ru.voodster.weatherstation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.voodster.weatherstation.R
import ru.voodster.weatherstation.databinding.ActivityMainBinding

class GraphFragment : Fragment() {

    private var binding : ActivityMainBinding? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_graph, container, false)
    }
    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}