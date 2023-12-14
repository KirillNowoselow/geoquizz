package com.example.geoquiz
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlin.math.roundToInt

private const val TAG = "MainActivity"
private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {
    private val quizViewModel: QuizViewModel by lazy {
       ViewModelProvider(this)[QuizViewModel::class.java]
    }

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageView
    private lateinit var backButton: ImageView
    private lateinit var questionTextView: TextView

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        backButton = findViewById(R.id.back_button)

        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
            arrowHandler()
            quizViewModel.alreadyAnswered = false

        }

        backButton.setOnClickListener {
            quizViewModel.moveToPrevious()
            arrowHandler()
            updateQuestion()
        }



        arrowHandler()
        updateQuestion()
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

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        if (userAnswer == correctAnswer && !quizViewModel.alreadyAnswered){
            quizViewModel.correctCount++
        }

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()

        quizViewModel.alreadyAnswered = true

        if (quizViewModel.currentIndex == questionBank.size - 1){
            val counter = quizViewModel.correctCount
            val percent = ((counter.toDouble() / questionBank.size.toDouble()) * 100).roundToInt()
            Toast.makeText(this, "Количество правильных ответов: $counter, процент: $percent %", Toast.LENGTH_SHORT)
                .show()

        }
    }

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex)
    }

    private fun arrowHandler(){
        val backgrounds = listOf(
            R.drawable.roundcorneryellow,
            R.drawable.roundcornergray,
        )
        if (quizViewModel.currentIndex > 0) backButton.background = getDrawable(backgrounds[0])
        else backButton.background = getDrawable(backgrounds[1])

        if (quizViewModel.currentIndex == questionBank.size - 1) nextButton.background = getDrawable(backgrounds[1])
        else nextButton.background = getDrawable(backgrounds[0])

    }
}