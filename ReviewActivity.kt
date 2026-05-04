package com.example.lifehackapp1

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        // Log that the review screen has loaded
        Log.d("ReviewActivity", "Review screen loaded")

        val layout = findViewById<LinearLayout>(R.id.reviewLayout)

        // Title
        val title = TextView(this)
        title.text = "Answer Review"
        title.textSize = 22f
        title.setTypeface(null, Typeface.BOLD)
        title.setTextColor(Color.parseColor("#F5E6C8"))
        title.setPadding(0, 0, 0, 32)
        layout.addView(title)

        val questions = arrayOf(
            "Putting your phone in rice fixes water damage",
            "Using keyboard shortcuts improves productivity",
            "Drinking coffee completely dehydrates you",
            "Writing tasks down improves memory",
            "Charging your phone overnight destroys the battery"
        )

        val answers = arrayOf(false, true, false, true, false)

        val explanations = arrayOf(
            "Rice absorbs some moisture but does not fix water damage.",
            "Shortcuts save time and seriously boost productivity.",
            "Coffee has a mild diuretic effect but still hydrates you.",
            "Writing by hand strengthens memory and retention.",
            "Modern phones have smart chips that stop overcharging."
        )

        // Loop through all questions and build a card for each one
        for (i in questions.indices) {

            // Log each question being added to the review screen
            Log.d("ReviewActivity", "Loading review card ${i + 1}: ${questions[i]}")

            val card = LinearLayout(this)
            card.orientation = LinearLayout.VERTICAL
            card.setPadding(24, 24, 24, 24)
            card.setBackgroundColor(Color.parseColor("#3B1A08"))
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(0, 0, 0, 16)
            card.layoutParams = params

            // Question text
            val qText = TextView(this)
            qText.text = "Q${i + 1}: ${questions[i]}"
            qText.textSize = 15f
            qText.setTextColor(Color.parseColor("#F5E6C8"))
            qText.setTypeface(null, Typeface.BOLD)
            qText.setPadding(0, 0, 0, 8)
            card.addView(qText)

            // Answer badge
            val answerText = TextView(this)
            val label = if (answers[i]) "HACK — True" else "MYTH — False"
            answerText.text = label
            answerText.textSize = 12f
            answerText.setTextColor(Color.parseColor("#1C0A00"))
            answerText.setBackgroundColor(Color.parseColor("#C47B2B"))
            answerText.setPadding(16, 6, 16, 6)
            answerText.setTypeface(null, Typeface.BOLD)
            card.addView(answerText)

            // Explanation text
            val expText = TextView(this)
            expText.text = explanations[i]
            expText.textSize = 13f
            expText.setTextColor(Color.parseColor("#9A8A7A"))
            expText.setPadding(0, 10, 0, 0)
            card.addView(expText)

            layout.addView(card)
        }

        // Log that all cards have been loaded
        Log.d("ReviewActivity", "All ${questions.size} review cards loaded successfully")
    }
}