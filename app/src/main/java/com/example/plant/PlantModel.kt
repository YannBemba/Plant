package com.example.plant

class PlantModel(
    val id: String = "plant0",
    val name: String = "Rose",
    val description: String = "Petie description",
    val imageUrl: String = "http://graven.yt/plante.jpg",
    val grow: String = "Faible",
    val water: String = "Moyenne",
    var liked: Boolean = false
)