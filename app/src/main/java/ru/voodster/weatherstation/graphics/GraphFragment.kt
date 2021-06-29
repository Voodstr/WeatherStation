package ru.voodster.weatherstation.graphics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.voodster.weatherstation.databinding.FragmentGraphBinding

class GraphFragment : Fragment() {

    companion object{
        const val TAG = "GraphFragment"
        fun newInstance(): GraphFragment {
            return GraphFragment()
        }

    }

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
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}