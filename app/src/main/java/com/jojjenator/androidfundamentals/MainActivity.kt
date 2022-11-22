package com.jojjenator.androidfundamentals

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// Inherits from AppCompatActivity, a class that describes what an activity is.
// We inherit from it to take over that behaviour and to make our own activity out of that.
class MainActivity : AppCompatActivity() {

    // OnCreate is inherited from AppCompatActivity, IE entrypoint for this activity and any other created activity.
    // CTRL + O to see whats overrideable, what is available from AppCompatActivity.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnShowToast = findViewById<Button>(R.id.btnShowToast)
        val btnOpenActivity = findViewById<Button>(R.id.btnOpenActivity)

        // Context provided "this" is ok because activities are subclasses to context class
        // Activity-context only lives as long as the activity does
        // - if you have a singleton object that needs the context and u need it to live as long as
        // - the application does then you should use app-context, if you use activity-context
        // - memory leak might occur.
        // Application-context lives for as long as the application.
        btnShowToast.setOnClickListener {
            Toast.makeText(this, "Hello!", Toast.LENGTH_SHORT).show()
        }

        // Intent to start SecondActivity
        // Inside constructor: CTRL + P to see accepted arguments
        btnOpenActivity.setOnClickListener {

            // PackageContext: THIS(activity context)
            // do not have to create instance of the second activity, only refer to the class
            // .also is a scope-function, much like .apply{} (...binding.apply{}).
            Intent(this, SecondActivity::class.java).also {
                // As we see inside also: it = Intent.
                startActivity(it) // startActivity takes intent, use it.
            }


        }
    }

}
