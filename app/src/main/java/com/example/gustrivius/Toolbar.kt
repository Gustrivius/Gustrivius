package com.example.gustrivius

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import com.example.gustrivius.databinding.ActivityToolbarBinding

class Toolbar : AppCompatActivity() {

    private lateinit var binding: ActivityToolbarBinding
    private val MenuLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {result ->}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar)

        binding = ActivityToolbarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.profileBackArrow.setOnClickListener{view: View ->
            val intent = Intent(this, MainActivity::class.java)
            //intent.putExtra("Q_id", Q_id)
            MenuLauncher.launch(intent)
        }
    }
}