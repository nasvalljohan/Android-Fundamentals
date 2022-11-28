package com.jojjenator.androidfundamentals.fragments


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.jojjenator.androidfundamentals.FifthActivity
import com.jojjenator.androidfundamentals.FourthActivity

import com.jojjenator.androidfundamentals.R

class BottomNav1 : Fragment(R.layout.fragment_bottom_nav1) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnNext = view.findViewById<Button>(R.id.btnFifthActivityNav)
        btnNext.setOnClickListener {
            Intent(activity, FifthActivity::class.java).also {
                startActivity(it)
            }

        }
    }

}