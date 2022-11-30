package com.jojjenator.androidfundamentals

import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.LinearLayoutCompat
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
        val dragView = findViewById<View>(R.id.dragView)
        val llTop = findViewById<LinearLayoutCompat>(R.id.llTop)
        val llBottom = findViewById<LinearLayoutCompat>(R.id.llBottom)

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


        // Drag & Drop view..

        // Set drag listener to the views
        llTop.setOnDragListener(dragListener)
        llBottom.setOnDragListener(dragListener)


        // The view to drag
        dragView.setOnLongClickListener{

            // Create text
            val clipText = "This is our clipData text"

            // Convert text to ClipData.item
            val item = ClipData.Item(clipText)

            // Mimetypes(file endings), what kind of data we want to put into clipData.
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)

            // Construct data
            val data = ClipData(clipText, mimeTypes, item)

            // DragShadowBuilder, give our view a shadow so we know that we are actually moving it.
            val dragShadowBuilder = View.DragShadowBuilder(it)

            // Start the drag & drop:
            it.startDragAndDrop(data, dragShadowBuilder, it, 0)

            // Because its now started we want to make our view invisible and only show shadow
            it.visibility = View.INVISIBLE

            // "return" true, we don't write return in lambda functions.
            true
        }

    }

    // Drag listener for dragView that we will attach to our LinearLayouts
    // - to be able to respond to different drag-events.
    // dragListener will be param for *VIEW*.setOnDragListener(HERE)
    // ** invalidate() means 'redraw on screen' and results to a call of the view's onDraw() **
    private val dragListener = View.OnDragListener{ view, dragEvent ->

        // When-expression to respond to dragEvents
        when(dragEvent.action){
            DragEvent.ACTION_DRAG_STARTED -> { // When drag started
                // If our drag object accept our drag data, will return true of false
                dragEvent.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)
            }
            DragEvent.ACTION_DRAG_ENTERED -> { // when our dragView enters the layouts boundaries
                view.invalidate() // We want to update our layouts view by invalidating
                true
            }
            DragEvent.ACTION_DRAG_LOCATION -> true // needs to be here but we wont use it, return true

            DragEvent.ACTION_DRAG_EXITED -> { // when our dragView leaves our layouts boundaries
                view.invalidate() // We want to update our layouts view by invalidating
                true
            }

            DragEvent.ACTION_DROP -> { // When we drop our dragView
                // First we want to get the attached data, which is at index 0.
                val item = dragEvent.clipData.getItemAt(0).text

                // Make a toast whenever we drop the dragView, just for demo
                Toast.makeText(this, item, Toast.LENGTH_SHORT).show()

                view.invalidate() // We want to update our layouts view by invalidating

                // Now we want to remove the view from the layout it was in and
                // put it into the new layout

                // We can get the dragged object to our dragView:
                val v = dragEvent.localState as View // v will be our dragView

                // Then we want the owner-layout where our dragView was before and remove it
                val owner = v.parent as ViewGroup
                owner.removeView(v)

                // Then we want to insert the dragView in the view it was dropped in
                val destination = view as LinearLayoutCompat
                destination.addView(v)

                // Finally set the visibility back to .VISIBLE
                // **  initially set invisible from onLongClickListener
                v.visibility = View.VISIBLE
                true
            }
            DragEvent.ACTION_DRAG_ENDED -> { // When drag has ended
                view.invalidate() // Invalidate our view and return true.
                true
            }
            else -> false // else needed for when-expression
        }

    }
}