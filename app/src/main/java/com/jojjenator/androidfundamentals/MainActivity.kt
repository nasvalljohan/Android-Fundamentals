package com.jojjenator.androidfundamentals

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// Inherits from AppCompatActivity, a class that describes what an activity is.
// We inherit from it to take over that behaviour and to make our own activity out of that.
class MainActivity : AppCompatActivity() {

    // OnCreate is inherited from AppCompatActivity, IE entrypoint for this activity and any other created activity.
    //CTRL + O to see whats overrideable, what is available from AppCompatActivity.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    override fun onPause() {
        super.onPause()
        println("onPause")
    }

    override fun onRestart() {
        super.onRestart()
        println("onRestart")
    }
}
