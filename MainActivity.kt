package com.example.lifehackapp1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity", "App started — Welcome screen loaded")

        // Find the Start button and listen for a click
        val startButton = findViewById<Button>(R.id.startButton)

        // When clicked, open the QuizActivity
        startButton.setOnClickListener {
            Log.d("MainActivity", "Start button clicked — moving to QuizActivity")
            startActivity(Intent(this, QuizActivity::class.java))
        }
    }
}