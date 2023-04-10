package com.example.gustrivius

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import com.example.gustrivius.databinding.ActivityMainBinding
import androidx.activity.viewModels

private lateinit var QA_Button: Button

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val QALauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {result ->}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.QAButton.setOnClickListener { view: View ->
            val intent = QAActivity.newIntent(this@MainActivity)
            QALauncher.launch(intent)
        }
    }
}