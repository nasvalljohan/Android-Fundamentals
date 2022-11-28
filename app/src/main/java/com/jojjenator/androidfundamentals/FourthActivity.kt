package com.jojjenator.androidfundamentals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jojjenator.androidfundamentals.fragments.*

// When demoing this activity, see how these fragments affect the current activity/fragment stack.
// - If you press back, does it go back to previous activity or does it show the newly replaced fragment?
// If you want to change this behaviour you can use "addToBackStack(null)" to push fragment onto the stack.

class FourthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)
        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val bottomnav1 = BottomNav1()
        val bottomnav2 = BottomNav2()
        val bottomnav3 = BottomNav3()


        setCurrentFragmentNav(bottomnav1)

        bottomNavView.setOnItemSelectedListener{
            when(it.itemId) {
                R.id.miHome -> setCurrentFragmentNav(bottomnav1)
                R.id.miMessages -> setCurrentFragmentNav(bottomnav2)
                R.id.miProfile -> setCurrentFragmentNav(bottomnav3)
            }
            //Expects bool, just return true.
            true
        }
        bottomNavView.getOrCreateBadge(R.id.miMessages).apply {
            this.number = 2
        }
    }

    private fun setCurrentFragmentNav(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.flFragment, fragment)
        commit()
    }
}
