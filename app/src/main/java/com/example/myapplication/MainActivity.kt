package com.example.forumactivity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val postList = mutableListOf<ForumPost>()
    private lateinit var adapter: ForumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = ForumAdapter(postList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val edtMessage = findViewById<EditText>(R.id.edtMessage)
        val btnPost = findViewById<Button>(R.id.btnPost)

        btnPost.setOnClickListener {
            val text = edtMessage.text.toString()
            if (text.isNotEmpty()) {
                // Get the current time (e.g., 8:48 PM)
                val currentTime = SimpleDateFormat("h:mm a", Locale.getDefault()).format(Date())

                // Create the post with the timestamp
                postList.add(ForumPost("You", text, currentTime))

                adapter.notifyItemInserted(postList.size - 1)
                edtMessage.text.clear()
                recyclerView.scrollToPosition(postList.size - 1)
            }
        }
    }
}
