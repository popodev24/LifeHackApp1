package com.example.lifehackapp1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score) // ← only activity_score

        // Get the score passed from QuizActivity
        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 0)

        Log.d("ScoreActivity", "Score screen loaded — Player scored $score out of $total")

        val scoreEmoji   = findViewById<TextView>(R.id.scoreEmoji)
        val scoreText    = findViewById<TextView>(R.id.scoreText)
        val scoreMessage = findViewById<TextView>(R.id.scoreMessage)
        val reviewButton = findViewById<Button>(R.id.reviewButton)
        val retryButton  = findViewById<Button>(R.id.retryButton)

        // Show different message based on score
        when {
            score == total -> {
                scoreEmoji.text   = "\uD83C\uDFC6"
                scoreMessage.text = "Perfect score! You are a Master Hacker!"
                Log.d("ScoreActivity", "Result: Perfect score")
            }
            score >= 3 -> {
                scoreEmoji.text   = "\uD83D\uDCAA"
                scoreMessage.text = "Great work! Almost there."
                Log.d("ScoreActivity", "Result: Good score")
            }
            else -> {
                scoreEmoji.text   = "\uD83D\uDCDA"
                scoreMessage.text = "Keep practising — you will get there!"
                Log.d("ScoreActivity", "Result: Low score — needs practice")
            }
        }

        scoreText.text = "You scored $score out of $total"

        // Go to review screen
        reviewButton.setOnClickListener {
            Log.d("ScoreActivity", "Review button clicked")
            startActivity(Intent(this, ReviewActivity::class.java))

        }

        // Restart the quiz
        retryButton.setOnClickListener {
            Log.d("ScoreActivity", "Retry button clicked — restarting quiz")
            startActivity(Intent(this, QuizActivity::class.java))
            finish()
        }
    }
}