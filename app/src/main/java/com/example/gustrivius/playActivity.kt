package com.example.gustrivius

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.example.gustrivius.databinding.ActivityPlayBinding
import com.example.gustrivius.databinding.ActivityQaactivityBinding

class playActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayBinding
    private val MenuLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {result ->}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.MenuButton.setOnClickListener { view: View ->
            val intent = Intent(this, QAActivity::class.java)
            MenuLauncher.launch(intent)
        }
    }

}