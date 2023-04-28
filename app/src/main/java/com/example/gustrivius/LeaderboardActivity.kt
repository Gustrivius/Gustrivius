package com.example.gustrivius

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.gustrivius.databinding.ActivityLeaderboardBinding
import com.example.gustrivius.databinding.ActivityQaactivityBinding

class LeaderboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLeaderboardBinding
    private val learderLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {result ->}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeaderboardBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val name = intent.getSerializableExtra("name").toString()
        //Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
        binding.textView1.text = name

        binding.MenuButton.setOnClickListener { view: View ->
            val intent = Intent(this, MainActivity::class.java)
            learderLauncher.launch(intent)
            //finish()
        }
    }
}