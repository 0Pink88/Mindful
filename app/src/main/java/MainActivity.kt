package com.example.forumactivity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val posts = mutableListOf<ForumPost>()
    private lateinit var adapter: ForumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Initialize Forum RecyclerView and Adapter
        val recyclerView = findViewById<RecyclerView>(R.id.forumRecyclerView)
        adapter = ForumAdapter(posts)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // 2. Setup the "Post" button and Input field for the Forum
        val postButton = findViewById<Button>(R.id.btnPost)
        val inputField = findViewById<EditText>(R.id.editPostInput)

        postButton.setOnClickListener {
            val message = inputField.text.toString().trim()
            if (message.isNotEmpty()) {
                // Add new post to the top of our list (index 0)
                posts.add(0, ForumPost(author = "Student", message = message))
                adapter.notifyItemInserted(0)
                recyclerView.scrollToPosition(0)
                inputField.text.clear()
            } else {
                Toast.makeText(this, "Type something first!", Toast.LENGTH_SHORT).show()
            }
        }

        // 3. Setup Notification Channel (Required for Android 8.0+)
        createNotificationChannel()

        // 4. Setup the Motivational Notification Button
        val notifButton = findViewById<Button>(R.id.notificationButton)
        notifButton.setOnClickListener {
            sendNotification()
            // Quick feedback to the user that the action happened
            Toast.makeText(this, "Motivational reminder sent!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Mindful Channel"
            val descriptionText = "Channel for Motivational Notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("MINDFUL_NOTIF", name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification() {
        // List of random quotes to satisfy the "Motivational" requirement
        val motivationalQuotes = listOf(
            "You're doing great today!",
            "Remember to take a deep breath.",
            "Your mental health is a priority.",
            "One step at a time.",
            "You are stronger than you think.",
            "Progress, not perfection."
        )

        // Pick one quote at random
        val randomQuote = motivationalQuotes.random()

        val builder = NotificationCompat.Builder(this, "MINDFUL_NOTIF")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Mindful Moment")
            .setContentText(randomQuote) // Displays the randomized motivational quote
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true) // Dismisses the notification when clicked

        try {
            with(NotificationManagerCompat.from(this)) {
                // 1 is the unique ID for this notification
                notify(1, builder.build())
            }
        } catch (e: SecurityException) {
            // Handles Android 13+ permission logic
            Toast.makeText(this, "Please enable notifications in settings", Toast.LENGTH_SHORT).show()
        }
    }
}