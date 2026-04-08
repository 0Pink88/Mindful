package com.example.forumactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// 1. Added likes to the data model
data class ForumPost(
    val author: String,
    val message: String,
    val timestamp: String,
    var likes: Int = 0,
    var isLiked: Boolean = false // Track the state
)

class ForumAdapter(
    private val postList: List<ForumPost>,
    private val onLikeClicked: (Int) -> Unit // 2. Callback to notify MainActivity
) : RecyclerView.Adapter<ForumAdapter.ForumViewHolder>() {

    class ForumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val authorText: TextView = itemView.findViewById(R.id.txtAuthor)
        val messageText: TextView = itemView.findViewById(R.id.txtMessage)
        val timeText: TextView = itemView.findViewById(R.id.txtTimestamp)
        val btnLike: android.widget.ImageButton = itemView.findViewById(R.id.btnLike) // Make sure this is ImageButton
        val txtLikeCount: TextView = itemView.findViewById(R.id.txtLikeCount) // Make sure this exists
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
        holder.txtLikeCount.text = currentPost.likes.toString()

        // Change the heart color based on the liked state
        if (currentPost.isLiked) {
            holder.btnLike.setColorFilter(android.graphics.Color.RED)
        } else {
            holder.btnLike.setColorFilter(android.graphics.Color.GRAY)
        }

        holder.btnLike.setOnClickListener {
            onLikeClicked(position)
        }
    }

    override fun getItemCount(): Int = postList.size
}