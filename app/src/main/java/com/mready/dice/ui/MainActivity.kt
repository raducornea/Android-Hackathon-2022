package com.mready.dice.ui

import android.content.Context
import android.content.SharedPreferences
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mready.dice.R
import com.mready.dice.ui.dialog.DoubleDialog
import com.mready.dice.ui.dice.DiceFragment
import com.mready.dice.ui.history.HistoryFragment
import com.squareup.seismic.ShakeDetector

class MainActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, DiceFragment.newInstance(), DiceFragment::class.simpleName)
            .commit()
    }

    fun obtineSystemService(): SensorManager {
        return getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    fun navigateToHistory() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                HistoryFragment.newInstance(),
                HistoryFragment::class.simpleName
            )
            .addToBackStack(HistoryFragment::class.simpleName)
            .commit()
    }

    fun navigateBack() {
        supportFragmentManager.popBackStackImmediate()
    }

    fun navigateDoubleDialog(){
        var dialog = DoubleDialog()
        dialog.show(supportFragmentManager, "customDialog")
    }



    fun showToast(){
        val toast = Toast.makeText(this, "ceva mesaj random", Toast.LENGTH_LONG)
        toast.show()
    }

    fun showToast(message: String){
        val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        toast.show()
    }

    fun getSharedPreferences(): SharedPreferences {
        return getSharedPreferences("SharedPreferences", MODE_PRIVATE)
    }
}