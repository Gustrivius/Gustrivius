package com.example.gustrivius

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.gustrivius.databinding.ActivityMainBinding
import androidx.activity.viewModels
import com.google.firebase.firestore.AggregateSource
import androidx.appcompat.app.AppCompatDelegate

private lateinit var QA_Button: Button
var QuestionID = ArrayList<String>()

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val QALauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {result ->}
    var username = "default";


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.playButton.isEnabled = false;

        binding.QAButton.setOnClickListener { view: View ->
            val intent = Intent(this, QAActivity::class.java)
            QALauncher.launch(intent)
        }

        binding.playButton.setOnClickListener { view: View ->
            val intent = Intent(this, playActivity::class.java)
            intent.putExtra("questionID", QuestionID)
            intent.putExtra("name", username)
            QALauncher.launch(intent)
        }

        binding.profileButton.setOnClickListener { view: View ->
            val intent = Intent(this, profileActivity::class.java)
            QALauncher.launch(intent)
        }

        /*binding.usernameButton.setOnClickListener {
            username = binding.usernameText.text.toString()
            binding.playButton.isEnabled = true
            binding.usernameText.getText().clear()*/

            /*db.collection("questions").count().get(AggregateSource.SERVER).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (task.result.count == 0L) {
                        Toast.makeText(this, "Please click Q&A button and add questions", Toast.LENGTH_SHORT).show()
                    }
                }
            }*/

            db.collection("questions")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        QuestionID.add(document.id)
                    }
                    //binding.playButton.isEnabled = true
                }
                .addOnFailureListener {}

        binding.nightmodeButton.setOnClickListener {
            if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        var fullname = intent.getSerializableExtra("userFullName")
        if (fullname.toString() == "null") {
            binding.includedProfPic.usernameView.text = "Gus Trivia"
            binding.playButton.isEnabled = false
        }
        else {
            binding.includedProfPic.usernameView.text = fullname.toString()
            binding.playButton.isEnabled = true
        }

        username = intent.getSerializableExtra("UserName").toString()

    }
}