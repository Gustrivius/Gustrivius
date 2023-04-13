package com.example.gustrivius

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.example.gustrivius.databinding.ActivityQaactivityBinding

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