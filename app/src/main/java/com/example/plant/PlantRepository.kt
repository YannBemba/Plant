package com.example.plant

import android.graphics.ColorSpace
import android.net.Uri
import com.example.plant.PlantRepository.Singleton.databaseRef
import com.example.plant.PlantRepository.Singleton.downloadUri
import com.example.plant.PlantRepository.Singleton.plantList
import com.example.plant.PlantRepository.Singleton.storageReference
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.util.*

class PlantRepository {

    object Singleton {
        // lien pour accéder au bucket
        private val BUCKET_URL: String = "gs://plantcollection-e475b.appspot.com"

        // Se connecter à la reference "plants"
        val databaseRef = FirebaseDatabase.getInstance().getReference("plants")

        // se connecter à notre espace de stockage
        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL)

        // Création d'une liste contenant nos plantes
        val plantList = arrayListOf<PlantModel>()

        // Contenir le lien de l'image courante
        var downloadUri: Uri? = null
    }

    fun updateData(callback: () -> Unit) {
        // absorber les données depuis la databaseRef -> liste de plantes
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // retirer les anciennes
                plantList.clear()

                // récolter la liste

                for(ds in snapshot.children) {
                    // construire un objet plante
                    val plant = ds.getValue(PlantModel::class.java)
                    
                    // vérifier que la plante n'est pas null
                    if(plant != null) {
                        // Ajouter la plante à notre liste
                        plantList.add(plant)
                    }
                }

                // actionner le callback
                callback()

            }

            override fun onCancelled(error: DatabaseError) {}

        })
    }

    // créer une fonction pour envoyer des fichiers sur le storage
    fun uploadImage(file: Uri, callback: () -> Unit) {
        // vérifier que ce fichier n'est pas null
        if(file != null) {
            val fileName = UUID.randomUUID().toString() + ".jpg"
            val ref = storageReference.child(fileName)
            val uploadTask = ref.putFile(file)

            // démarrer la tâche d'envoyer
            uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->

                // si il y a eu un problème dans l'envoi du fichier
                if(!task.isSuccessful) {
                    task.exception.let { throw it!! }
                }
                return@Continuation ref.downloadUrl

            }).addOnCompleteListener { task ->
                //vérifier si tout a bien fonctionné
                if(task.isSuccessful) {
                    // récupérer l'image
                    downloadUri = task.result
                    callback()
                }
            }
        }
    }

    // insérer une nouvelle plante en bdd
    fun insertPlant(plant: PlantModel) = databaseRef.child(plant.id).setValue(plant)

    // mettre à jour un objet plante en bdd
    fun updatePlant(plant: PlantModel) = databaseRef.child(plant.id).setValue(plant)

    // supprimer une plante de la base
    fun deletePlant(plant: PlantModel) = databaseRef.child(plant.id).removeValue()

}