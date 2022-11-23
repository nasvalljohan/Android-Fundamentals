package com.jojjenator.androidfundamentals

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
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
        val etName = findViewById<EditText>(R.id.etName)
        val etAge = findViewById<EditText>(R.id.etAge)
        val etCountry = findViewById<EditText>(R.id.etCountry)

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

            val name = etName.text.toString()
            val age = etAge.text.toString().toInt()
            val country = etCountry.text.toString()
            // New person, pass in created variables
            val person = Person(name, age, country)

            // PackageContext: THIS(activity context)
            // do not have to create instance of the second activity, only refer to the class
            // .also is a scope-function, much like .apply{} (...binding.apply{}).
            Intent(this, SecondActivity::class.java).also {

                // Put extra serialized
                it.putExtra("EXTRA_PERSON", person)
                // As we see inside also: it = Intent.
                startActivity(it) // startActivity takes intent, use it.
            }

        }
    }

    // Inflate menu-layout
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    // Onclick-handler
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.miAddContact -> Toast.makeText(this, "Add contact clicked", Toast.LENGTH_SHORT).show()
            R.id.miFavorites -> Toast.makeText(this, "Favorites clicked", Toast.LENGTH_SHORT).show()
            R.id.miSettings -> Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show()
            R.id.miClose -> finish()
            R.id.miFeedback -> Toast.makeText(this, "Feedback clicked", Toast.LENGTH_SHORT).show()

        }
        return true
    }

}
