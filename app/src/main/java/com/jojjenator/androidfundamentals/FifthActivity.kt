package com.jojjenator.androidfundamentals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
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
        //viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL

        // With these you can simulate drags without doing it to provide helpful UX.
        //viewPager.beginFakeDrag()
        //viewPager.fakeDragBy(-10f)
        //viewPager.endFakeDrag()

        val tabLayout = findViewById<TabLayout>(R.id.tabLayoutPagerItems)

        // Connect tabLayout with the existing viewPager.
        // Instead of .setUpWithViewPager which is deprecated

        // Lambda function, takes two params -> we called them tab and position.
        TabLayoutMediator(tabLayout, viewPager){tab, position ->
            tab.text = "Tab ${position + 1}"
        }.attach() // -> to attach this mediator to the views.

        // How to add listener for selected tab:
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Toast.makeText(this@FifthActivity, "Selected ${tab?.text}", Toast.LENGTH_SHORT).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@FifthActivity, "UnSelected ${tab?.text}", Toast.LENGTH_SHORT).show()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Toast.makeText(this@FifthActivity, "Reselected ${tab?.text}", Toast.LENGTH_SHORT).show()
            }
        })




    }
}