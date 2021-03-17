package com.example.plant.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.plant.MainActivity
import com.example.plant.PlantModel
import com.example.plant.R

class PlantAdapter(
        private val context: MainActivity,
        private val plantList: List<PlantModel>,
        private val layoutId: Int): RecyclerView.Adapter<PlantAdapter.ViewHolder>() {

    class ViewHolder(view: View)
        : RecyclerView.ViewHolder(view){
        val plantImage: ImageView = view.findViewById(R.id.image_item)
        val plantName: TextView? = view.findViewById(R.id.name_item)
        val plantDesc: TextView? = view.findViewById(R.id.description_item)
        val starIcon: ImageView = view.findViewById(R.id.star_icon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Récupérer les informations de la plante
        val currentPlant = plantList[position]

        holder.plantName?.text = currentPlant.name
        holder.plantDesc?.text = currentPlant.description

        // Récupérer les images avec Glide
        Glide.with(context)
                .load(Uri.parse(currentPlant.imageUrl))
                .into(holder.plantImage)

        // Vérifier si la plante a été liké

        if(currentPlant.liked) {
            holder.starIcon.setImageResource(R.drawable.ic_star)
        } else {
            holder.starIcon.setImageResource(R.drawable.ic_unstar)

        }

    }

    override fun getItemCount(): Int = plantList.size

}