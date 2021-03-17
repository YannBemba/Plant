package com.example.plant.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.plant.MainActivity
import com.example.plant.PlantModel
import com.example.plant.R
import com.example.plant.adapter.PlantAdapter
import com.example.plant.adapter.PlantItemDecoration
import com.example.plant.databinding.FragmentHomeBinding
import kotlin.collections.arrayListOf as arrayListOf

class HomeFragment(
        private val context: MainActivity
) : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)

        // créer une liste qui va stocker ces plantes
        val plantList = arrayListOf<PlantModel>()

        //enregistrer une première plante dans notre liste (pissenlit)
        plantList.add(PlantModel(
                "Rose",
                "C'est ma fleur préférée",
                "https://cdn.pixabay.com/photo/2015/04/19/08/32/rose-729509__340.jpg",
                false
        ))
        plantList.add(PlantModel(
                "PissenLit",
                "Jaune soleil",
                "https://cdn.pixabay.com/photo/2018/05/20/16/13/dandelion-3416140__340.jpg",
                false
        ))
        plantList.add(PlantModel(
                "Cactus",
                "Ca pique !!l",
                "https://cdn.pixabay.com/photo/2020/07/24/16/37/cactus-5434469__340.jpg",
                false
        ))
        plantList.add(PlantModel(
                "Tulipe",
                "Woah",
                "https://cdn.pixabay.com/photo/2017/03/30/18/38/tulip-2189317__340.jpg",
                false
        ))

        // Récupérer le recyclerView

        val horizontalRecyclerView = binding.horizontalRecyclerview
        horizontalRecyclerView.adapter = PlantAdapter(context, plantList, R.layout.item_horizontal_plant)

        // Récupérer le second recyclerView

        val verticalRecyclerView = binding.verticalRecyclerview
        verticalRecyclerView.adapter = PlantAdapter(context, plantList, R.layout.item_vertical_plant)

        verticalRecyclerView.addItemDecoration(PlantItemDecoration())

        return binding.root
    }
}