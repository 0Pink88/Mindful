package com.example.mindfulfinal

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input = findViewById<EditText>(R.id.inputText)
        val analyzeBtn = findViewById<Button>(R.id.analyzeButton)
        val resultTxt = findViewById<TextView>(R.id.resultText)
        val resetBtn = findViewById<Button>(R.id.resetButton)

        analyzeBtn.setOnClickListener {
            val text = input.text.toString().trim().lowercase()
            if (text.isNotEmpty()) {
                val mood = when {
                    text.contains("happy") || text.contains("good") -> "Positive ✨"
                    text.contains("sad") || text.contains("bad") -> "Needs care 💙"
                    text.contains("angry") || text.contains("mad") -> "Frustrated 😤"
                    else -> "Neutral 😐"
                }
                resultTxt.text = "Detected Mood: $mood"
            } else {
                resultTxt.text = "Please type something!"
            }
        }

        resetBtn.setOnClickListener {
            input.text.clear()
            resultTxt.text = ""
        }
    }
}