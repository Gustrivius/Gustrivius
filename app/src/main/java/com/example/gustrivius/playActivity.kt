package com.example.gustrivius

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.gustrivius.databinding.ActivityPlayBinding
import com.example.gustrivius.databinding.ActivityQaactivityBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class playActivity : AppCompatActivity() {
    val db = Firebase.firestore
    private lateinit var binding: ActivityPlayBinding
    private val MenuLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {result ->}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        //All the code for getting data from Firebase
        val doc = db.collection("questions").document("CYTuCQZditkNMzOdaTqd")
            FirebaseFirestore.getInstance().collection("questions").get()
            doc.get().addOnSuccessListener { DocumentSnapShot ->
                //val questions = documentSnapshot.toObject<questions>()
                if (DocumentSnapShot != null) {
                    //val obj = document.data.toObject<questions>()
                    Toast.makeText(this, DocumentSnapShot.get("text").toString(), Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener { exception -> Toast.makeText(this, "error", Toast.LENGTH_SHORT).show() }
        */
        binding.MenuButton.setOnClickListener { view: View ->
        }
    }

}