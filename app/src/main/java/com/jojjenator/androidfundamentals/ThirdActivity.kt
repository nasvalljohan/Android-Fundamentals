package com.jojjenator.androidfundamentals

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts

class ThirdActivity : AppCompatActivity() {

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
    }

}