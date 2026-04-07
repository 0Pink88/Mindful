package com.example.forumactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Updated to include a timestamp
data class ForumPost(
    val author: String,
    val message: String,
    val timestamp: String
)

class ForumAdapter(private val postList: List<ForumPost>) :
    RecyclerView.Adapter<ForumAdapter.ForumViewHolder>() {

    class ForumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val authorText: TextView = itemView.findViewById(R.id.txtAuthor)
        val messageText: TextView = itemView.findViewById(R.id.txtMessage)
        val timeText: TextView = itemView.findViewById(R.id.txtTimestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForumViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return ForumViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForumViewHolder, position: Int) {
        val currentPost = postList[position]
        holder.authorText.text = currentPost.author
        holder.messageText.text = currentPost.message
        holder.timeText.text = currentPost.timestamp
    }

    override fun getItemCount(): Int = postList.size
}