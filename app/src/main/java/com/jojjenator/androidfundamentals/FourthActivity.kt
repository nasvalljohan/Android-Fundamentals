package com.jojjenator.androidfundamentals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FourthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fourth)
        val btnFragment1 = findViewById<Button>(R.id.btnFragment1)
        val btnFragment2 = findViewById<Button>(R.id.btnFragment2)
        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()

        // In order to swap between first- and secondFragment, we need to use a fragment-transaction:
        supportFragmentManager.beginTransaction().apply {
            // Replace needs current containerID(=our frameLayout)
            // Second param is which fragment to replace current with, if current == null it just sets fragment.
            replace(R.id.flFragment, firstFragment)

            // Commit will apply the changes to our transaction.
            commit() // Now firstFragment will be visible in flFragment-container.
        }

        btnFragment1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, firstFragment) //Replace secondFragment with firstFragment
                commit()
            }
        }

        btnFragment2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, secondFragment) //Replace firstFragment with secondFragment
                commit()
            }
        }

        // When demoing this activity, see how these fragments affect the current activity/fragment stack.
        // - If you press back, does it go back to previous activity or does it show the newly replaced fragment?
        // If you want to change this behaviour you can use "addToBackStack(null)" to push fragment onto the stack.



    }
}