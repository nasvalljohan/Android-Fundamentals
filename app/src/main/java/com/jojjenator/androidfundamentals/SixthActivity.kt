package com.jojjenator.androidfundamentals

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class SixthActivity : AppCompatActivity() {

    // Create channelID and channelName for notification
    private val CHANNEL_ID = "ChannelID"
    private val CHANNEL_NAME = "ChannelName"
    private val NOTIFICATION_ID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sixth)

        val btnSave = findViewById<Button>(R.id.btnSavePref)
        val btnLoad = findViewById<Button>(R.id.btnLoadPref)
        val btnNext = findViewById<Button>(R.id.btnGoToSeventh)
        val et1 = findViewById<EditText>(R.id.etSharedPref1)
        val et2 = findViewById<EditText>(R.id.etSharedPref2)
        val btnNotification = findViewById<Button>(R.id.btnShowNotification)

        btnNext.setOnClickListener {
            Intent(this, SeventhActivity::class.java).also {
                startActivity(it)
            }
        }

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

        // Create the notificationChannel
        createNotificationChannel()

        // To be able to navigate somewhere when click on the notification:
        // We "give" access bit of our code so other apps can execute it.
        // Create the navigation:
        val intent = Intent(this, FifthActivity::class.java)

        // PendingIntent allows other apps to execute a piece of code from our app
        //In this case we can use pending intent to start our activity when notification is clicked
        val pendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent) // Add this
            getPendingIntent(0,PendingIntent.FLAG_IMMUTABLE)
        }

        // Now create the notification to post in our channel:
        // Pass context and pass in what channel we want to put our notification
        val notification = NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("NOTIFICATION")
            .setContentText("This is notification text")
            .setSmallIcon(R.drawable.ic_favorites)
            .setContentIntent(pendingIntent) // What happens when the notification is clicked

            // Don't confuse this with importance
            // This sets prio-relationship between the notifications in the channel, not for the channel
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build() // Now build to return a notification.

        val notificationManager = NotificationManagerCompat.from(this)

        btnNotification.setOnClickListener {
            notificationManager.notify(NOTIFICATION_ID, notification)
        }
    }

    //Create the notification channel
    fun createNotificationChannel(){
        //Might have to check version of phoneSDK here.

        // CHANNEL::::::::::::::::::::
        //the higher importance of notification the more will happen, e.g. sound, vibration, light etc
        val channel = NotificationChannel(CHANNEL_ID,CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH).apply {
                lightColor = Color.GREEN
                enableLights(true)
            }

        // MANAGER:::::::::::::::::::
        // After channel is created we need to create a manager that actually creates the notification channel
        // This returns type "ANY", therefore we need to cast it as a NotificationManager instead
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //Here is where we create the actual NotificationChannel
        manager.createNotificationChannel(channel)

    }
}