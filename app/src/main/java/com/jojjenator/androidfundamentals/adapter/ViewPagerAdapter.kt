package com.jojjenator.androidfundamentals.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.jojjenator.androidfundamentals.R

// Images as list of int because R.id.... is an integer.
class ViewPagerAdapter(val images: List<Int>): RecyclerView.Adapter<ViewPagerAdapter.viewPagerViewHolder>() {

    inner class viewPagerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewPagerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_pager, parent, false)

        return viewPagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: viewPagerViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.ivViewPagerImage)
        val currentImage = images[position]
        imageView.setImageResource(currentImage)
    }

    override fun getItemCount(): Int {
        return images.size
    }


}