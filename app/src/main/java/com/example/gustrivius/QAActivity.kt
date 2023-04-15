package com.example.gustrivius

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.gustrivius.databinding.ActivityQaactivityBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

private const val TAG = "QAActivity"
val db = Firebase.firestore

class QAActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQaactivityBinding
    private lateinit var database: DatabaseReference
    private val MenuLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {result ->}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQaactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var n = 1

        binding.SubmitButton.setOnClickListener { view: View ->
            val text = binding.editText1.text.toString()
            val correctAnswer = binding.editText2.text.toString()
            val wrong1 = binding.editText3.text.toString()
            val wrong2 = binding.editText4.text.toString()
            val wrong3 = binding.editText5.text.toString()
            database = FirebaseDatabase.getInstance().getReference("Questions")
            val question = Question(n, text, correctAnswer, wrong1, wrong2,wrong3)
            database.child(n.toString()).setValue(question).addOnSuccessListener {
                binding.editText1.text.clear()
                binding.editText2.text.clear()
                binding.editText3.text.clear()
                binding.editText4.text.clear()
                binding.editText5.text.clear()
                n++
                Toast.makeText(this, "Successfully submit the question, keep submit next question", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Submit the question failed, please let the designers know", Toast.LENGTH_SHORT).show()
            }
        }

        binding.MenuButton.setOnClickListener { view: View ->
            /*val intent = Intent(this, QAActivity::class.java)
            MenuLauncher.launch(intent)*/
            finish()
        }
    }
}

private fun submit(question:Question) {
    db.collection("questions")
    .add(question)
        .addOnSuccessListener { documentReference ->
            Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            Log.w(TAG, "Error adding document", e)
        }
}