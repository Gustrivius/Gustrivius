package com.example.gustrivius

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.example.gustrivius.databinding.ActivityQaactivityBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private const val TAG = "QAActivity"
val db = Firebase.firestore

class QAActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQaactivityBinding
    private val MenuLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {result ->}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQaactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.MenuButton.setOnClickListener { view: View ->
            val intent = Intent(this, QAActivity::class.java)
            MenuLauncher.launch(intent)
        }
    }
}

private fun submit(question: Question) {
    db.collection("questions")
    .add(question)
        .addOnSuccessListener { documentReference ->
            Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error adding document", e)
        }
}