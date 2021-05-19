package ru.voodster.weatherstation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.voodster.weatherstation.databinding.ActivityMainBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager: ViewPager2



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        lifecycle.addObserver(App.instance!!.weatherUpdater)
        //App.instance!!.weatherUpdater.setLifecycle(lifecycle)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = binding.pager

        val  pagerAdapter = ScreenSlidePagerAdapter(this)
        pagerAdapter.addFragment(IndicationFragment())
        pagerAdapter.addFragment(TableFragment())
        pagerAdapter.addFragment(GraphFragment())

        viewPager.adapter=pagerAdapter


    }


    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        private val fragmentList: MutableList<Fragment> = ArrayList()

        override fun createFragment(position: Int): Fragment {
                return fragmentList[position]
        }

        override fun getItemCount(): Int {
            return fragmentList.size
        }

        fun addFragment(fragment: Fragment) {
            fragmentList.add(fragment)
        }




    }






}