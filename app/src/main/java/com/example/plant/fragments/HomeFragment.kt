package com.example.plant.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.plant.MainActivity
import com.example.plant.PlantRepository.Singleton.plantList
import com.example.plant.R
import com.example.plant.adapter.PlantAdapter
import com.example.plant.adapter.PlantItemDecoration
import com.example.plant.databinding.FragmentHomeBinding

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

        // Récupérer le recyclerView

        val horizontalRecyclerView = binding.horizontalRecyclerview
        horizontalRecyclerView.adapter = PlantAdapter(context, plantList.filter { !it.liked }, R.layout.item_horizontal_plant)

        // Récupérer le second recyclerView

        val verticalRecyclerView = binding.verticalRecyclerview
        verticalRecyclerView.adapter = PlantAdapter(context, plantList, R.layout.item_vertical_plant)

        verticalRecyclerView.addItemDecoration(PlantItemDecoration())

        return binding.root
    }
}