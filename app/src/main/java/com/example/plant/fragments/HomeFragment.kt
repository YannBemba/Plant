package com.example.plant.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.plant.R
import com.example.plant.adapter.PlantAdapter
import com.example.plant.adapter.PlantItemDecoration
import com.example.plant.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

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
        horizontalRecyclerView.adapter = PlantAdapter(R.layout.item_horizontal_plant)

        val verticalRecyclerView = binding.verticalRecyclerview
        verticalRecyclerView.adapter = PlantAdapter(R.layout.item_vertical_plant)

        verticalRecyclerView.addItemDecoration(PlantItemDecoration())

        return binding.root
    }
}