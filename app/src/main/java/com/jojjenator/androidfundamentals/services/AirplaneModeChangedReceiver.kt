package com.jojjenator.androidfundamentals.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneModeChangedReceiver: BroadcastReceiver(){

    // In this function we put our code that should execute when user sets AirplaneMode

    // Broadcast is always sent with an intent
    /* When airplaneMode changes, system will look which apps want to be notified by that change
    ..and then send out intent to those apps */
    override fun onReceive(context: Context?, intent: Intent?) {

        /* To respond to this we create variable to collect the intent, in this case its called a "state"
        * variable is nullable, to make sure its not null we can go "?: return", if null return out. */
        val isAirplaneModeEnabled = intent?.getBooleanExtra("state", false) ?: return

        if(isAirplaneModeEnabled) {
            Toast.makeText(context, "AirplaneMode Enabled", Toast.LENGTH_LONG).show()
        }else {
            Toast.makeText(context, "AirplaneMode Disabled", Toast.LENGTH_LONG).show()
        }

    }

}