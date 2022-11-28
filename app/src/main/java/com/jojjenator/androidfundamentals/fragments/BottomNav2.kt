package com.jojjenator.androidfundamentals.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import com.jojjenator.androidfundamentals.R
import com.jojjenator.androidfundamentals.SixthActivity


class BottomNav2 : Fragment(R.layout.fragment_bottom_nav2) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnNext = view.findViewById<Button>(R.id.btnSixthActivityNav)
        btnNext.setOnClickListener {
            Intent(activity, SixthActivity::class.java).also {
                startActivity(it)
            }

        }
    }
}