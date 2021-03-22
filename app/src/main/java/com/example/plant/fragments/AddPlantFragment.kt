package com.example.plant.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.plant.MainActivity
import com.example.plant.databinding.FragmentAddPlantBinding

class AddPlantFragment(
        private val context: MainActivity
): Fragment() {

    private var _binding: FragmentAddPlantBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?)
    : View {
        _binding = FragmentAddPlantBinding.inflate(layoutInflater)

        return binding.root
    }

}