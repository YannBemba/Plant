package com.example.plant.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plant.MainActivity
import com.example.plant.PlantRepository.Singleton.plantList
import com.example.plant.R
import com.example.plant.adapter.PlantAdapter
import com.example.plant.adapter.PlantItemDecoration
import com.example.plant.databinding.FragmentCollectionBinding

class CollectionFragment(
        private val context: MainActivity
): Fragment() {

    private var _binding: FragmentCollectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?)
    : View {
        _binding = FragmentCollectionBinding.inflate(layoutInflater)

        // récupérer ma recyclerview

        val collectionRecyclerView = binding.collectionRecyclerList

        collectionRecyclerView.adapter = PlantAdapter(context, plantList.filter { it.liked }, R.layout.item_vertical_plant)
        collectionRecyclerView.layoutManager = LinearLayoutManager(context)
        collectionRecyclerView.addItemDecoration(PlantItemDecoration())

        return binding.root
    }

}