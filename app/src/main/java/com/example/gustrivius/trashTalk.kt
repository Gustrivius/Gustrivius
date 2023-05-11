package com.example.gustrivius

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gustrivius.databinding.ActivityLeaderboardBinding
import com.example.gustrivius.databinding.ActivityTrashTalkBinding
import com.google.firebase.firestore.*

class trashTalk : AppCompatActivity() {
    private lateinit var binding: ActivityTrashTalkBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var messageList : ArrayList<message>
    private lateinit var myAdapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrashTalkBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_trash_talk)
        setContentView(binding.root)

        recyclerView = findViewById(R.id.trashRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.setHasFixedSize(true)

        messageList = arrayListOf()

        myAdapter = MessageAdapter(messageList)

        recyclerView.adapter = myAdapter
        //val name = intent.getSerializableExtra("name").toString()
        //Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
        //binding.textView1.text = name

        RankChangeListener()

        binding.MessageSubmitButton.setOnClickListener {
            val message = message(binding.editText1.text.toString(), intent.getStringExtra("name").toString(), System.currentTimeMillis()/1000L)
            db.collection("messages").add(message).addOnSuccessListener {
                    documentReference ->
                //Q_id.add(documentReference.id)
                Toast.makeText(this, "Successfully submitted message", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Submit failed", Toast.LENGTH_SHORT).show()
            }
            binding.editText1.getText().clear()
        }
    }
    private fun RankChangeListener(){
        FirebaseFirestore.getInstance().collection("messages").orderBy("time", Query.Direction.ASCENDING)
            .addSnapshotListener(object: com.google.firebase.firestore.EventListener<QuerySnapshot> {
                override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?
                ){
                    for (dc : DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            messageList.add(dc.document.toObject(message::class.java))
                        }
                    }

                    myAdapter.notifyDataSetChanged()
                }
            })
    }
}

