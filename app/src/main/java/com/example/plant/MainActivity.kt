package com.example.plant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.plant.databinding.ActivityMainBinding
import com.example.plant.fragments.AddPlantFragment
import com.example.plant.fragments.CollectionFragment
import com.example.plant.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(HomeFragment(this), R.string.home_page_title)

        // importer la bottomnavigationview
        val navigationView = binding.navigationView
        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home_page -> {
                    loadFragment(HomeFragment(this), R.string.home_page_title)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.collection_page -> {
                    loadFragment(CollectionFragment(this), R.string.collection_page_title)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.add_plant_page -> {
                    loadFragment(AddPlantFragment(this), R.string.add_plant_page_title)
                    return@setOnNavigationItemSelectedListener true
                }

                else -> false
            }
        }

    }

    private fun loadFragment(fragment: Fragment, title: Int) {
        // charger notre repository
        val repo = PlantRepository()

        // actualiser le titre de la page
        binding.pageTitle.text = resources.getString(title)

        // mettre à jour la liste de plantes
        repo.updateData {
            // Injecter le fragment dans notre boîte (fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}