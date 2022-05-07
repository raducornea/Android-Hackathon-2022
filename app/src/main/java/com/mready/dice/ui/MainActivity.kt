package com.mready.dice.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mready.dice.R
import com.mready.dice.ui.dice.DiceFragment
import com.mready.dice.ui.history.HistoryFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.container, DiceFragment.newInstance(), DiceFragment::class.simpleName)
            .commit()
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

    fun showToast(){
        val toast = Toast.makeText(this, "ceva mesaj random", Toast.LENGTH_SHORT)
        toast.show()
    }

}