package com.jojjenator.androidfundamentals

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SixthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sixth)
        val btnSave = findViewById<Button>(R.id.btnSavePref)
        val btnLoad = findViewById<Button>(R.id.btnLoadPref)
        val et1 = findViewById<EditText>(R.id.etSharedPref1)
        val et2 = findViewById<EditText>(R.id.etSharedPref2)

        // Create new sharedPref with:
        // Name: by providing name we can read values from multiple sharedPrefs
        // Mode: How we want our sharedPrefs to be;
        // - public = public, any other app can access the shared pref
        // - private = private, no other app can read preferences
        // - append = appending new prefs to existing.
        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        // Whenever we want to write to shared prefs we need its editor
        val editor = sharedPref.edit()

        btnSave.setOnClickListener {
            val secret1 = et1.text.toString()
            val secret2 = et2.text.toString()

            editor.apply{
                putString("Secret 1", secret1)
                putString("Secret 2", secret2)
                apply() // apply is async.
                // commit() also works but is not async, it will block mainthread.
            }
        }

        btnLoad.setOnClickListener {
            // In constructor: Incase key "Secret 1" does not exist, default "Nothing here.." will show
            val secret1 = sharedPref.getString("Secret 1", "Nothing here..")
            val secret2 = sharedPref.getString("Secret 2", "Nothing here..")

            et1.hint = secret1
            et2.hint = secret2
        }


    }
}