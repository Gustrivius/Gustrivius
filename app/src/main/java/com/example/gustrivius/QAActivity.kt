package com.example.gustrivius

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gustrivius.databinding.ActivityQaactivityBinding

class QAActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQaactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQaactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}