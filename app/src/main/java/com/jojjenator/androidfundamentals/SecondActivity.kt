package com.jojjenator.androidfundamentals

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val btnBack = findViewById<Button>(R.id.btnBack)
        val tvPerson = findViewById<TextView>(R.id.tvPerson)
        val btnNext = findViewById<Button>(R.id.btnOpenThirdActivity)

        // getSerializableExtra does not return a Person
        // Cast it as Person to convert it back
        val person = intent.getSerializableExtra("EXTRA_PERSON") as Person

        // person now cast to Person, can now use person.name etc
        tvPerson.text = person.toString()


        btnBack.setOnClickListener {
            // finish() clears the current activity from the activity-stack
            finish()
        }

        btnNext.setOnClickListener {
            Intent(this, ThirdActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}