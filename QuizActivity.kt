package com.example.lifehackapp1

import android.util.Log
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    // Track which question we're on and the score
    var index = 0
    var score = 0

    // UI elements
    lateinit var questionText: TextView
    lateinit var questionNumber: TextView
    lateinit var feedbackText: TextView
    lateinit var nextButton: Button
    lateinit var hackButton: Button
    lateinit var mythButton: Button

    // All 5 questions
    val questions = arrayOf(
        "Putting your phone in rice fixes water damage",
        "Using keyboard shortcuts improves productivity",
        "Drinking coffee completely dehydrates you",
        "Writing tasks down improves memory",
        "Charging your phone overnight destroys the battery"
    )

    // Correct answers — true = Hack, false = Myth
    val answers = arrayOf(false, true, false, true, false)

    // Explanation shown after each answer
    val explanations = arrayOf(
        "Myth: Rice absorbs some moisture but does not fix water damage.",
        "Hack: Shortcuts save time and seriously boost productivity.",
        "Myth: Coffee has a mild diuretic effect but still hydrates you.",
        "Hack: Writing by hand strengthens memory and retention.",
        "Myth: Modern phones have smart chips that stop overcharging."
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz) // ← only activity_quiz, NOT activity_main

        // Connect UI elements to their ids
        questionText   = findViewById(R.id.questionText)
        questionNumber = findViewById(R.id.questionNumber)
        feedbackText   = findViewById(R.id.feedbackText)
        nextButton     = findViewById(R.id.nextButton)
        hackButton     = findViewById(R.id.hackButton)
        mythButton     = findViewById(R.id.mythButton)

        // Load the first question
        loadQuestion()

        // Hack button clicked — user says TRUE
        hackButton.setOnClickListener { checkAnswer(true) }

        // Myth button clicked — user says FALSE
        mythButton.setOnClickListener { checkAnswer(false) }

        // Next button — move to next question
        nextButton.setOnClickListener {
            index++

            if (index < questions.size) {
                // More questions — load next one
                loadQuestion()
                feedbackText.text = ""
                nextButton.visibility = android.view.View.GONE
                hackButton.isEnabled = true
                mythButton.isEnabled = true
            } else {
                // No more questions — go to score screen
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("total", questions.size)
                startActivity(intent)
                finish()
            }
        }
    }

    // Loads the current question onto the screen
    fun loadQuestion() {
        questionNumber.text = "Question ${index + 1} of ${questions.size}"
        questionText.text   = questions[index]
    }

    // Checks if the user's answer matches the correct answer
    fun checkAnswer(userAnswer: Boolean) {
        // Log the user's answer to the console for debugging
        Log.d("QuizActivity", "User answered: $userAnswer, Correct: ${answers[index]}")

        if (userAnswer == answers[index]) {
            feedbackText.text      = "Correct! \uD83C\uDF89\n${explanations[index]}"
            feedbackText.setTextColor(Color.parseColor("#4CAF50"))
            score++
        } else {
            feedbackText.text      = "Wrong! \u274C\n${explanations[index]}"
            feedbackText.setTextColor(Color.parseColor("#E53E3E"))
        }

        // Disable both buttons after answering
        hackButton.isEnabled = false
        mythButton.isEnabled = false

        // Show the Next button
        nextButton.visibility = android.view.View.VISIBLE
    }
}