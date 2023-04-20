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
    private val MenuLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {result ->}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQaactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var n = 1
        var Q_id : ArrayList<String> = ArrayList()
        //var Q_id = intent.getStringExtra("Q_id")

        binding.SubmitButton.setOnClickListener { view: View ->
            val questions = questions(
                n,
                binding.editText1.text.toString(),
                binding.editText2.text.toString(),
                binding.editText3.text.toString(),
                binding.editText4.text.toString(),
                binding.editText5.text.toString()
            )
            db.collection("questions").add(questions).addOnSuccessListener {
                documentReference ->
                n++
                Q_id.add(documentReference.id)
                Toast.makeText(this, "Successfully submit the question, keep submit next question", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Submit the question failed, please let the designers know", Toast.LENGTH_SHORT).show()
            }
        }

        binding.MenuButton.setOnClickListener { view: View ->
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("Q_id", Q_id)
            MenuLauncher.launch(intent)
        }
    }

}
