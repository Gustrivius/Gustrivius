package com.example.gustrivius

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.example.gustrivius.databinding.ActivityPlayBinding
import com.example.gustrivius.databinding.ActivityQaactivityBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
var QID = ArrayList<String>()
var correct_score = 0
var click= 0;

class playActivity : AppCompatActivity()  {
    val db = Firebase.firestore
    private lateinit var binding: ActivityPlayBinding
    private val MenuLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {result ->}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var ids = intent.getSerializableExtra("questionID") as ArrayList<String>
        for (i in 0..(ids.size - 1)) {
            QID.add(ids[i])
        }
        QID.shuffle()

        //var questionTextView = findViewById(R.id.question_text);

        //All the code for getting data from Firebase
        //while(true) {
        moveToNext();
    }
    fun moveToNext() {
        if (click == QID.size) {
            click--;

            val name = intent.getSerializableExtra("name").toString()
            val user = user(name, correct_score)
            db.collection("leaderboard").add(user).addOnSuccessListener {
            }

            AlertDialog.Builder (this)
                .setTitle("Done")
                .setMessage("Congratulations, you answered all the questions, the score is: $correct_score")
                .setPositiveButton("Back to menu") {dialogInterface, i -> finish()}
                .setCancelable(false)
                .show()

            val intent = Intent(this, LeaderboardActivity::class.java)
            MenuLauncher.launch(intent)
        }
        val doc = db.collection("questions").document(QID[click])
        FirebaseFirestore.getInstance().collection("questions").get()
        doc.get().addOnSuccessListener { DocumentSnapShot ->
            //val questions = documentSnapshot.toObject<questions>()
            if (DocumentSnapShot != null) {
                val answerChoice = arrayOf(
                    DocumentSnapShot.get("correctAnswer").toString(),
                    DocumentSnapShot.get("wrongAnswer1").toString(),
                    DocumentSnapShot.get("wrongAnswer2").toString(),
                    DocumentSnapShot.get("wrongAnswer3").toString()
                )
                answerChoice.shuffle()
                binding.questionText.text = DocumentSnapShot.get("text").toString()
                binding.choice1.text = answerChoice[0]
                binding.choice2.text = answerChoice[1]
                binding.choice3.text = answerChoice[2]
                binding.choice4.text = answerChoice[3]
                //val obj = document.data.toObject<questions>()
                //Toast.makeText(this, DocumentSnapShot.get("text").toString(), Toast.LENGTH_SHORT).show()
                binding.choice1.setOnClickListener { view: View ->
                    if (binding.choice1.text == DocumentSnapShot.get("correctAnswer")
                            .toString()
                    ) {
                        correct_score++;
                        binding.score.text = correct_score.toString();
                        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show()
                    }
                    click++;
                    moveToNext()
                }

                binding.choice2.setOnClickListener { view: View ->
                    if (binding.choice2.text == DocumentSnapShot.get("correctAnswer")
                            .toString()
                    ) {
                        correct_score++;
                        binding.score.text = correct_score.toString();
                        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show()
                    }
                    click++;
                    moveToNext()
                }

                binding.choice3.setOnClickListener { view: View ->
                    if (binding.choice3.text == DocumentSnapShot.get("correctAnswer")
                            .toString()
                    ) {
                        correct_score++;
                        binding.score.text = correct_score.toString();
                        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show()
                    }
                    click++;
                    moveToNext()
                }

                binding.choice4.setOnClickListener { view: View ->
                    if (binding.choice4.text == DocumentSnapShot.get("correctAnswer")
                            .toString()
                    ) {
                        correct_score++;
                        binding.score.text = correct_score.toString();
                        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show()
                    }
                    click++;
                    moveToNext()
                }
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(
                this,
                "error",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

}