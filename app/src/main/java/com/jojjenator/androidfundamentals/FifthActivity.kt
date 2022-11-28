package com.jojjenator.androidfundamentals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jojjenator.androidfundamentals.adapter.ViewPagerAdapter

class FifthActivity : AppCompatActivity() {

    // lateinit is a promise. we promise to init this later.
    // benefit of this is that variable is global in class.
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifth)

        val imageList = listOf(
            R.drawable.playstore_snuslogo,
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

        // Navigation drawer:
        var drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)

        // Here we init out lateinit toggle
        // 1st param activity, 2nd param layout, 3rd and 4th are used for accessibility, e.g. for blind people.
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)

        // Add toggle to our layout
        drawerLayout.addDrawerListener(toggle)

        // After its connected we need to tell our toggle that is should sync its state, we tell it its now ready to be used.
        toggle.syncState()

        // When we toggle, the toggle button moves to a arrow button - open and close drawer.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var navView = findViewById<NavigationView>(R.id.navView)
        navView.setNavigationItemSelectedListener {
            // it == the current menu item that was clicked
            when(it.itemId){
                R.id.miItem1 -> Toast.makeText(applicationContext, "clicked item 1", Toast.LENGTH_SHORT).show()
                R.id.miItem2 -> Toast.makeText(applicationContext, "clicked item 2", Toast.LENGTH_SHORT).show()
                R.id.miItem3 -> Toast.makeText(applicationContext, "clicked item 3", Toast.LENGTH_SHORT).show()
            }
            // true == return out of lambda application
            true
        }
    }

    // This is where we need our toggle-variable to be global, its used in new override function.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        // if user clicked on toggle-button
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}