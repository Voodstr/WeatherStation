package ru.voodster.weatherstation

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import ru.voodster.weatherstation.databinding.ActivityMainBinding
import ru.voodster.weatherstation.graphics.GraphFragment
import ru.voodster.weatherstation.indication.IndicationFragment
import ru.voodster.weatherstation.table.TableFragment


class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG = "MainActivity"
    }

    private lateinit var binding: ActivityMainBinding

    val viewModel:WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindViews()
        openWeather()
    }


    private fun bindViews(){
        setNavigationBar()
    }

    private fun setNavigationBar(){
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_indication -> openWeather()
                R.id.nav_table -> openTable()
                R.id.nav_graph -> openGraph()
                R.id.nav_settings -> openSettings()
            }
            true
        }
    }

    private fun openGraph() {
        Log.d(TAG,"openGraph")
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.enter_right_toleftt,R.anim.exit_left_toright)
            .replace(R.id.fragmentContainer, GraphFragment.newInstance())
            .commit()
    }

    private fun openTable() {
        Log.d(TAG,"openTable")
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.enter_right_toleftt,R.anim.exit_left_toright)
            .replace(R.id.fragmentContainer, TableFragment.newInstance())
            .commit()
    }

    private fun openWeather() {
        Log.d(TAG,"openWeather")
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.enter_right_toleftt,R.anim.exit_left_toright)
            .replace(R.id.fragmentContainer, IndicationFragment.newInstance())
            .commit()
    }
    private fun openSettings() {
        Log.d(TAG,"openSettings")
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.enter_right_toleftt,R.anim.exit_left_toright)
            .replace(R.id.fragmentContainer, SettingsFragment.newInstance())
            .commit()
    }


    override fun onBackPressed() {

        if (supportFragmentManager.backStackEntryCount>0){
            supportFragmentManager.popBackStack()
        }else{
            val exitDialogBuilder = AlertDialog.Builder(this)
            exitDialogBuilder.setTitle(R.string.exitDialogTitle)
            exitDialogBuilder.setMessage(R.string.exitDialogText)
            exitDialogBuilder.setCancelable(true)
            exitDialogBuilder.setPositiveButton(R.string.yesBtn
            ) { _, _ ->
                super.onBackPressed()
            }
            val b = exitDialogBuilder.create()
            b.show()
        }

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
    }

}