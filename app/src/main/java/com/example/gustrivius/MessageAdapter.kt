package com.example.gustrivius

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MessageAdapter (private val messageList: ArrayList<message>):
    RecyclerView.Adapter<MessageAdapter.ViewHolder>() {
    //class MyHoler(val binding: TaskItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val task : message = messageList[position]

        holder.username.text = task.user
        holder.message.text = task.text

    }

    override fun getItemCount(): Int {
        return messageList.size
    }
    public class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView){
        val username : TextView = itemView.findViewById(R.id.usernameText)
        val message: TextView = itemView.findViewById(R.id.messageText)
    }
}
