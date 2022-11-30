package com.jojjenator.androidfundamentals

import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import com.jojjenator.androidfundamentals.services.AirplaneModeChangedReceiver

class ThirdActivity : AppCompatActivity() {

    private lateinit var receiver: AirplaneModeChangedReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

        val takePhoto = findViewById<Button>(R.id.btnGetImage)
        val imageView = findViewById<ImageView>(R.id.ivPhoto)
        val btnNext = findViewById<Button>(R.id.btnGoToFourth)

        // getContent gets called within intent. ser imageview to uri if its not null.
        val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            if (uri != null){
                imageView.setImageURI(uri)
            }
        }

        // Implicit intent, earlier only used explicit intent
        takePhoto.setOnClickListener {

            Intent(Intent.ACTION_GET_CONTENT).also {
                // looks for type image/* (* == all), can specify image/jpeg or image/png etc
                getContent.launch("image/*")

            }
        }

        btnNext.setOnClickListener {
            Intent(this, FourthActivity::class.java).also {
                startActivity(it)
            }

        }

        // BroadcastReceiver

        /*
        Define IntentFilter
        Used by the system to determine which apps want to receive which intents
        There is a variety of actions you can respond to with IntentFilter, check them out!
        */

        // ..This is a dynamic receiver..
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            // Create instance of AirplaneModeChangedReceiver
            receiver = AirplaneModeChangedReceiver()

            /*
            Register the receiver for the intent
            This will actually cause memory-leak, context in airplaneMode.. class holds context
            referencing to this activity where we created the receiver(instance of the class)
            Imagine we close the activity, broadcast receiver is still active and will hold
            context to our activity even tho its closed. We need onStop()
             */
            registerReceiver(receiver, it)
        }

    }

    override fun onStop(){
        super.onStop()
        /* When activity is closed we now explicitly close our broadcastReceiver */
        unregisterReceiver(receiver)

    }

}