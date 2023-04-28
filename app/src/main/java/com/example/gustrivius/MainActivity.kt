package com.example.gustrivius

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.gustrivius.databinding.ActivityMainBinding
import androidx.activity.viewModels
import com.google.firebase.firestore.AggregateSource

private lateinit var QA_Button: Button

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val QALauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {result ->}
    public var username = "defaultUsername";

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
        var QuestionID = intent.getSerializableExtra("Q_id")
        //Toast.makeText(this, intent.getSerializableExtra("Q_id").toString(), Toast.LENGTH_SHORT).show()
        binding.playButton.setOnClickListener { view: View ->
            val intent = Intent(this, playActivity::class.java)
            intent.putExtra("questionID", QuestionID)
            intent.putExtra("name", username)
            QALauncher.launch(intent)
        }

        binding.usernameButton.setOnClickListener {
            username = binding.usernameText.text.toString()
            db.collection("questions").count().get(AggregateSource.SERVER).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (task.result.count == 0L) {
                        Toast.makeText(this, "Please click Q&A button and add questions", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        binding.playButton.isEnabled = true;
                    }
                }
            }
        }
    }
}