package com.jojjenator.androidfundamentals

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.jojjenator.androidfundamentals.data.Person

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
        val spMonths = findViewById<Spinner>(R.id.spMonths)

        // Create spinner content on runtime:
        val customList = listOf("first","second","third","fourth")
        val adapter = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, customList)
        spMonths.adapter = adapter

        // Create spinner with static data defined in predefined layout-file.
        spMonths.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, View: View?, position: Int, id: Long) {
                if (adapterView != null) {
                    Toast.makeText(
                        this@MainActivity,
                        "Current item selected: ${adapterView.getItemAtPosition(position)}",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

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

        val options = arrayOf("First", "Second", "Third")


        val addContactDialog = AlertDialog.Builder(this)
            .setTitle("Want to add contect oskar??")
            .setMessage("mmm...")
            .setIcon(R.drawable.ic_add_contact)
            .setPositiveButton("Yes!"){_,_ ->
                Toast.makeText(this, "CLICKY", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No!"){_,_->
                Toast.makeText(this, "CLACKY", Toast.LENGTH_SHORT).show()
            }.create()

        val singleChoiceDialog = AlertDialog.Builder(this)
            .setTitle("Make your choice!!!!")
            .setSingleChoiceItems(options, 0){_,i->
                Toast.makeText(this, "You clicked in ${options[i]}", Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton("I COMPLY!"){_,_ ->
                Toast.makeText(this, "CLICKY", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("I WILL NOT COMPLY!"){_,_->
                Toast.makeText(this, "CLACKY", Toast.LENGTH_SHORT).show()
            }.create()

        val multiChoiceDialog = AlertDialog.Builder(this)
            .setTitle("Make your choice!!!!")
            .setMultiChoiceItems(options, booleanArrayOf(false,false,false)){_,i,isChecked ->
                if(isChecked) {
                    Toast.makeText(this, "You checked ${options[i]}", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "You unchecked ${options[i]}", Toast.LENGTH_SHORT).show()
                }
            }
            .setPositiveButton("I COMPLY!"){_,_ ->
                Toast.makeText(this, "CLICKY", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("I WILL NOT COMPLY!"){_,_->
                Toast.makeText(this, "CLACKY", Toast.LENGTH_SHORT).show()
            }.create()


        when (item.itemId){
            R.id.miAddContact -> addContactDialog.show()
            R.id.miFavorites -> singleChoiceDialog.show()
            R.id.miSettings -> multiChoiceDialog.show()
            R.id.miClose -> finish()
            R.id.miFeedback -> Toast.makeText(this, "Feedback clicked", Toast.LENGTH_SHORT).show()

        }
        return true
    }

}
