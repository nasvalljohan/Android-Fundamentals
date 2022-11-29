package com.jojjenator.androidfundamentals.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService: Service() {
    override fun onBind(p0: Intent?): IBinder? = null
    // Need to implement onBind because we MyService inherits from Service-class.
    // This is rarely used, its a method required if you have multiple clients that want to connect to the service at the same time

    // Main difference between Service and IntentService:
    // IntentService does NOT by default run on mainThread.
    // IntentService does NOT support multiThreading
    // When creating a service-class it will run on the mainThread by default.

    val TAG = "MyService"

    init {
        Log.d(TAG, "Service is running!")
    }

    // Important function for services: onStartCommand
    // - Its used to deliver the intent we started the service with
    // - We can also attach data to that intent to communicate from our activity to our service
    // - eg. make our service do different things in different situations
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        // Get the data from the intent:
        val dataString = intent?.getStringExtra("EXTRA_DATA")

        //Check if data is null
        dataString?.let {
            Log.d(TAG, dataString)
        }

        // Choose what to return, do not return the super.onStartCommand...
        // When system kills our service, what do we want to do?
        // Cases:
        // START_NOT_STICKY
        // - If system kills service(lack of resource), we wont recreate it when resources are free.
        // START_STICKY
        // - If system kills service(lack of resource, we will recreate when possible
        // - Service will be recreated with intent == null.
        // START_REDELIVER_INTENT
        // - Like START_STICKY but we will redeliver intent and not recreate with intent == null

        // If you have complex calculations that take time to execute:
        // Always start them in a separate thread like below
        Thread {
            // Infinite while-loop
            while(true){ }
        }

        return START_STICKY

    }

    // How to detect when service is being destroyed:
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Service is being killed")
    }

}