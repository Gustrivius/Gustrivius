package com.example.gustrivius

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gustrivius.databinding.ActivityLeaderboardBinding
import com.google.firebase.firestore.*

class LeaderboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLeaderboardBinding
    private val learderLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {result ->}

    private lateinit var recyclerView: RecyclerView
    private lateinit var rankList : ArrayList<user>
    private lateinit var myAdapter: RankAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeaderboardBinding.inflate(layoutInflater)

        setContentView(binding.root)

        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.setHasFixedSize(true)

        rankList = arrayListOf()

        myAdapter = RankAdapter(rankList)

        recyclerView.adapter = myAdapter
        //val name = intent.getSerializableExtra("name").toString()
        //Toast.makeText(this, name, Toast.LENGTH_SHORT).show()
        //binding.textView1.text = name

        RankChangeListener()


        binding.MenuButton.setOnClickListener { view: View ->
            //val intent = Intent(this, MainActivity::class.java)
            //learderLauncher.launch(intent)
            //finishAffinity()
            val i = Intent(this, MainActivity::class.java)
            finish()
            overridePendingTransition(0, 0)
            startActivity(i)
            overridePendingTransition(0, 0)
        }
    }

    private fun RankChangeListener(){
        FirebaseFirestore.getInstance().collection("leaderboard").orderBy("score", Query.Direction.DESCENDING)
            .addSnapshotListener(object: com.google.firebase.firestore.EventListener<QuerySnapshot> {
                override fun onEvent(
                    value: QuerySnapshot?,
                    error: FirebaseFirestoreException?
                ){
                    for (dc : DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            rankList.add(dc.document.toObject(user::class.java))
                        }
                    }

                    myAdapter.notifyDataSetChanged()
                }
        })
    }
}
