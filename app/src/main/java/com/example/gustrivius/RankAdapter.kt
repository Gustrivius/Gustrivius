package com.example.gustrivius

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class RankAdapter (private val rankList: ArrayList<user>):
    RecyclerView.Adapter<RankAdapter.ViewHolder>() {
        //class MyHoler(val binding: TaskItemBinding): RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val view = LayoutInflater.from(parent.context).inflate(R.layout.rank_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val task : user = rankList[position]

            holder.username.text = task.username
            holder.score.text = task.score.toString()

        }

        override fun getItemCount(): Int {
            return rankList.size
        }
    public class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        val username : TextView = itemView.findViewById(R.id.usernameText)
        val score: TextView = itemView.findViewById(R.id.scoreText)
    }
    }
