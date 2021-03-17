package com.example.plant.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.plant.R

class PlantAdapter(private val layoutId: Int): RecyclerView.Adapter<PlantAdapter.ViewHolder>() {

    class ViewHolder(view: View)
        : RecyclerView.ViewHolder(view){
        val plantImage: ImageView = view.findViewById(R.id.image_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {}

    override fun getItemCount(): Int = 5

}