package com.example.plant.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.plant.MainActivity
import com.example.plant.PlantModel
import com.example.plant.PlantRepository
import com.example.plant.PlantRepository.Singleton.downloadUri
import com.example.plant.databinding.FragmentAddPlantBinding
import java.util.*

class AddPlantFragment(
    private val context: MainActivity
): Fragment() {

    private var _binding: FragmentAddPlantBinding? = null
    private val binding get() = _binding!!

    private var uploadedImage: ImageView? = null
    private var file: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?)
            : View {
        _binding = FragmentAddPlantBinding.inflate(layoutInflater)

        // récupérer uploadedImage pour lui associer un composant
        uploadedImage = binding.previewImage

        // récupérer le bouton pour charger l'image

        val pickupImageButton = binding.uploadButton
        //lorsqu'on clique dessus ça ouvre les images du téléphone

        pickupImageButton.setOnClickListener {
            pickupImage()
        }

        // récupérer le bouton confirmer
        val confirmButton = binding.confirmButton

        confirmButton.setOnClickListener {
            sendForm(binding)
        }

        return binding.root
    }

    private fun sendForm(binding: FragmentAddPlantBinding) {
        // héberger sur le bucket
        val repo = PlantRepository()
        repo.uploadImage(file!!) {
            val plantName = binding.nameInput.text.toString()
            val plantDesc = binding.descriptionInput.text.toString()
            val grow = binding.growSpinner.selectedItem.toString()
            val water = binding.waterSpinner.selectedItem.toString()
            val downloadImageUrl = downloadUri

            // créer un nouvel objet PlantModel
            val plant = PlantModel(
                UUID.randomUUID().toString(),
                plantName,
                plantDesc,
                downloadImageUrl.toString(),
                grow,
                water
            )

            // envoyer en db
            repo.insertPlant(plant)
        }


    }


    private fun pickupImage() {
        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(intent, "Select Picture"),
            23
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 23 && resultCode == Activity.RESULT_OK) {
            // vérifier si les données sont null
            if(data == null || data.data == null) return

            // récupérer l'image sélectionnée
            file = data.data

            // mettre à jour l'aperçu de l'image
            uploadedImage?.setImageURI(file)

        }
    }

}