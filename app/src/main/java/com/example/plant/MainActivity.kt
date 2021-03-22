package com.example.plant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.plant.databinding.ActivityMainBinding
import com.example.plant.fragments.CollectionFragment
import com.example.plant.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // charger notre repository
        val repo = PlantRepository()

        // mettre à jour la liste de plantes
        repo.updateData {
            // Injecter le fragment dans notre boîte (fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, CollectionFragment(this))
            transaction.addToBackStack(null)
            transaction.commit()
        }



    }
}