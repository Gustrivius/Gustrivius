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

        var ids = intent.getSerializableExtra("questionID") as ArrayList<String>
        for (i in 0..(ids.size - 1)){
            QID.add(ids[i])
        }
        QID.shuffle()

        //var questionTextView = findViewById(R.id.question_text);

        //All the code for getting data from Firebase
        val doc = db.collection("questions").document(QID[0])
            FirebaseFirestore.getInstance().collection("questions").get()
            doc.get().addOnSuccessListener { DocumentSnapShot ->
                //val questions = documentSnapshot.toObject<questions>()
                if (DocumentSnapShot != null) {
                    binding.questionText.text = DocumentSnapShot.get("text").toString()
                    binding.choice1.text = DocumentSnapShot.get("correctAnswer").toString()
                    binding.choice2.text = DocumentSnapShot.get("wrongAnswer1").toString()
                    binding.choice3.text = DocumentSnapShot.get("wrongAnswer2").toString()
                    binding.choice4.text = DocumentSnapShot.get("wrongAnswer3").toString()
                            //val obj = document.data.toObject<questions>()
                    //Toast.makeText(this, DocumentSnapShot.get("text").toString(), Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener { exception -> Toast.makeText(this, "error", Toast.LENGTH_SHORT).show() }

        /*binding.MenuButton.setOnClickListener { view: View ->
            finish()
        }*/
    }

}