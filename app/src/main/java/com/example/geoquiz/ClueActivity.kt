package com.example.geoquiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

private const val TAG = "ClueActivity"
private const val KEY_INDEX = "index"

class ClueActivity: AppCompatActivity() {

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProvider(this)[QuizViewModel::class.java]
    }

    private lateinit var showAnswerButton: Button
    private lateinit var sureAnswerTextView: TextView
    private lateinit var answerTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_clue)

        val answer = intent.getBooleanExtra("answer", true)

        sureAnswerTextView = findViewById(R.id.clue_sure_text)
        showAnswerButton = findViewById(R.id.clue_answer_button)
        answerTextView = findViewById(R.id.clue_answer)

        showAnswerButton.setOnClickListener{
            if (answer) answerTextView.text = "Правда"
            else answerTextView.text = "Ложь"
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
    }

}