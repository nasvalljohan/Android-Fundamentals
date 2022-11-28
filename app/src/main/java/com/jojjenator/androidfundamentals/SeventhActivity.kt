package com.jojjenator.androidfundamentals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.jojjenator.androidfundamentals.services.MyIntentService

class SeventhActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seventh)
        var btnStart = findViewById<Button>(R.id.btnStart)
        var btnStop = findViewById<Button>(R.id.btnStop)
        var tvService = findViewById<TextView>(R.id.tvService)

        btnStart.setOnClickListener {
            Intent(this, MyIntentService::class.java).also {
                startService(it)
            }
            tvService.text = "Service running!"
        }

        btnStop.setOnClickListener {
            MyIntentService.stopService()
            tvService.text = "Service is stopped!"
        }

    }
}