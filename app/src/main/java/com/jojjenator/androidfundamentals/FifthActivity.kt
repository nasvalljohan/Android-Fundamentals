package com.jojjenator.androidfundamentals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.jojjenator.androidfundamentals.adapter.ViewPagerAdapter

class FifthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifth)

        val imageList = listOf(
            R.drawable.asset_2snuslogo,
            R.drawable.playstore1,
            R.drawable.playstore2,
            R.drawable.playstore3,
            R.drawable.playstore4
        )

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)

        // Create adapter
        val adapter = ViewPagerAdapter(imageList)

        // Set adapter to newly created adapter
        viewPager.adapter = adapter

        // This changes the scrolling orientation
        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL

        // With these you can simulate drags without doing it to provide helpful UX.
        viewPager.beginFakeDrag()
        viewPager.fakeDragBy(-10f)
        viewPager.endFakeDrag()



    }
}