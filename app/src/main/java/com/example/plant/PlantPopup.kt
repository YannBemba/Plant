package com.example.plant

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.plant.adapter.PlantAdapter
import com.example.plant.databinding.PopupPlantsDetailBinding

class PlantPopup(
        private val adapter: PlantAdapter,
        private val currentPlant: PlantModel
        ) : Dialog(adapter.context) {

    private lateinit var binding: PopupPlantsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = PopupPlantsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpComponents()
        setUpCloseButton()
        setUpDeleteButton()
        setUpLikeButton()
    }

    private fun updateStar(button: ImageView) {
        if(currentPlant.liked) {
            button.setImageResource(R.drawable.ic_star)
        } else {
            button.setImageResource(R.drawable.ic_unstar)
        }
    }

    private fun setUpLikeButton() {
        val starButton = binding.starButton

        updateStar(starButton)

        // interaction
        starButton.setOnClickListener {
            currentPlant.liked = !currentPlant.liked
            val repo = PlantRepository()
            repo.updatePlant(currentPlant)

            updateStar(starButton)
        }
    }

    private fun setUpDeleteButton() {
        binding.deleteButton.setOnClickListener {
            // supprimer la plante de la db
            val repo = PlantRepository()
            repo.deletePlant(currentPlant)
            dismiss()
        }
    }

    private fun setUpCloseButton() {
        binding.closeButton.setOnClickListener {
            // fermer la fenêtre
            dismiss()
        }
    }

    private fun setUpComponents() {
        // actualiser l'image de la plante
        val plantImage = binding.imageItem
        Glide.with(adapter.context)
                .load(Uri.parse(currentPlant.imageUrl))
                .into(plantImage)

        // actualiser le nom de la plante
        binding.popupPlantName.text = currentPlant.name

        // actualiser la description de la plante
        binding.popupPlantDescriptionSubtitle.text = currentPlant.description

        // actualiser la croissance de la plante
        binding.popupPlantGrowSubtitle.text = currentPlant.grow

        // actualiser la consommation d'eau de la plante
        binding.popupPlantWaterSubtitle.text = currentPlant.water


    }
}