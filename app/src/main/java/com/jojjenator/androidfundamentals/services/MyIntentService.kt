package com.jojjenator.androidfundamentals.services

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MyIntentService: IntentService("Intent Service") {

    init {
        instance = this
    }


    // Create a singleton
    companion object {
        @Deprecated("Deprecated in Java", ReplaceWith("JobIntentService or WorkManager\")"))
        private lateinit var instance: MyIntentService
        var isRunning = false

        fun stopService(){
            Log.d("MyIntentService","Service is stopping...")
            isRunning = false
            instance.stopSelf()
        }
    }

    // This function will process the intents we pass to the service we started
    // It will take the intents we pass, one at a time.
    override fun onHandleIntent(p0: Intent?) {
        try {
            isRunning = true
            while(isRunning){
                Log.d("MyIntentService","Service is running!")
                Thread.sleep(1000)
            }
        }catch(e: InterruptedException){
            Thread.currentThread().interrupt()
        }
    }


}