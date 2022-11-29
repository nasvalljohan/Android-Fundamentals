package com.jojjenator.androidfundamentals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.jojjenator.androidfundamentals.services.MyIntentService
import com.jojjenator.androidfundamentals.services.MyService

class SeventhActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seventh)
        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnStop = findViewById<Button>(R.id.btnStop)
        val tvService = findViewById<TextView>(R.id.tvService)
        val btnStartService = findViewById<Button>(R.id.btnStartService)
        val btnStopService = findViewById<Button>(R.id.btnStopService)
        val btnSendData = findViewById<Button>(R.id.btnSendData)
        val etSendData = findViewById<EditText>(R.id.etSendData)
        val tvServiceStatus = findViewById<TextView>(R.id.tvServiceStatus)


        // IntentService
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

        // Service
        btnStartService.setOnClickListener {
            Intent(this, MyService::class.java).also {
                startService(it)
                tvServiceStatus.text = "Service is now running!"
            }
        }
        btnStopService.setOnClickListener {
            Intent(this, MyService::class.java).also {
                // Also possible to make a companion object to stop service..
                stopService(it)
                tvServiceStatus.text = "Service stopped!"
            }
        }

        btnSendData.setOnClickListener {
            Intent(this, MyService::class.java).also {
                val dataString = etSendData.text.toString()
                it.putExtra("EXTRA_DATA", dataString)

                // Even if service is already running from btnStartService-click:
                // It wont restart, onStartCommand will be called with new intent with attached dataString
                // dataString wont be null and let-block will be executed.
                startService(it)
            }
        }

    }
}