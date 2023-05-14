package com.example.gustrivius

import android.R
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.gustrivius.databinding.ActivityProfileBinding


class profileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val MenuLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {result ->}

    var username = "default username"

    @SuppressLint("UseCompatLoadingForColorStateLists")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.includedProfLayout.profileBackArrow.setOnClickListener{view: View ->
            val intent = Intent(this, MainActivity::class.java)
            var fullname = binding.firstNameEntry.text.toString().plus(" ").plus(binding.lastNameEntry.text.toString())
            intent.putExtra("userFullName", fullname)

            val intent2 = Intent(this, playActivity::class.java)
            var username = binding.usernameEntry.text.toString()
            intent2.putExtra("UserFullName", username)
            MenuLauncher.launch(intent)
        }

        binding.firstNameEntry.isEnabled = false
        binding.lastNameEntry.isEnabled = false
        binding.usernameEntry.isEnabled = false
        binding.emailEntry.isEnabled = false

        binding.editButton.setOnClickListener {
            binding.firstNameEntry.isEnabled = true
            binding.lastNameEntry.isEnabled = true
            binding.usernameEntry.isEnabled = true
            binding.emailEntry.isEnabled = true
            binding.firstNameEntry.setTextColor(resources.getColorStateList(R.color.darker_gray))
            binding.lastNameEntry.setTextColor(resources.getColorStateList(R.color.darker_gray))
            binding.usernameEntry.setTextColor(resources.getColorStateList(R.color.darker_gray))
            binding.emailEntry.setTextColor(resources.getColorStateList(R.color.darker_gray))
        }

        binding.profSubmitButton.setOnClickListener {
            binding.firstNameEntry.isEnabled = false
            binding.lastNameEntry.isEnabled = false
            binding.usernameEntry.isEnabled = false
            binding.emailEntry.isEnabled = false
            binding.firstNameEntry.setTextColor(resources.getColorStateList(R.color.black))
            binding.lastNameEntry.setTextColor(resources.getColorStateList(R.color.black))
            binding.usernameEntry.setTextColor(resources.getColorStateList(R.color.black))
            binding.emailEntry.setTextColor(resources.getColorStateList(R.color.black))
            binding.includedProfPic.usernameView.text = binding.firstNameEntry.text.toString().plus(" ").plus(binding.lastNameEntry.text.toString())

            val userinfo = user(
                binding.firstNameEntry.text.toString(),
                binding.lastNameEntry.text.toString(),
                binding.usernameEntry.text.toString(),
                binding.emailEntry.text.toString(),
            )
            db.collection("user").add(userinfo).addOnSuccessListener {
                    documentReference ->
                //Q_id.add(documentReference.id)
                Toast.makeText(this, "Your information was successfully submitted.", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Your information failed to submit, please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}