package com.herdal.flagquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.herdal.flagquizapp.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding
    private lateinit var questions: ArrayList<Flag>
    // we have 3 wrong answers and 1 correct answer
    private lateinit var wrongAnswers: ArrayList<Flag>
    private lateinit var correctQuestion: Flag
    private lateinit var allOptions: HashSet<Flag>
    private lateinit var dbHelper: DatabaseHelper

    private var questionCounter = 0
    private var correctAnswerCounter = 0
    private var wrongAnswerCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        dbHelper = DatabaseHelper(this)

        val flagDao = FlagDao()

        questions = flagDao.getRandom5Flags(dbHelper)

        loadQuestion()

        binding.buttonA.setOnClickListener {
            isAnswerCorrect(binding.buttonA)
            questionCounterControl()
        }
        binding.buttonB.setOnClickListener {
            isAnswerCorrect(binding.buttonB)
            questionCounterControl()
        }
        binding.buttonC.setOnClickListener {
            isAnswerCorrect(binding.buttonC)
            questionCounterControl()
        }
        binding.buttonD.setOnClickListener {
            isAnswerCorrect(binding.buttonD)
            questionCounterControl()
        }
    }




    // load question into quiz layout page
    fun loadQuestion() {
        binding.textViewQuestionNumber.text = "Question : ${questionCounter+1}" // get question number

        correctQuestion = questions.get(questionCounter) // get correct answer

        binding.imageViewFlag.setImageResource(resources.getIdentifier(correctQuestion.imageName,"drawable",packageName))

        val flagDao = FlagDao()

        wrongAnswers = flagDao.getRandom3WrongAnswers(dbHelper,correctQuestion.id)

        allOptions = HashSet()
        allOptions.add(correctQuestion)
        allOptions.add(wrongAnswers.get(0))
        allOptions.add(wrongAnswers.get(1))
        allOptions.add(wrongAnswers.get(2))

        binding.buttonA.text = allOptions.elementAt(0).name
        binding.buttonB.text = allOptions.elementAt(1).name
        binding.buttonC.text = allOptions.elementAt(2).name
        binding.buttonD.text = allOptions.elementAt(3).name
    }

    fun questionCounterControl() {
        questionCounter++
        if(questionCounter != 5) { // 5 questions. game is not over
            loadQuestion()
        } else { // game's over. intent to result activity
            val intent = Intent(this@QuizActivity,ResultActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun isAnswerCorrect(button: Button) { // checks answer is correct or wrong and increments counters
        val buttonText = button.text.toString()
        var correctAnswer = correctQuestion.name

        if(buttonText == correctAnswer) { // answer is correct
            correctAnswerCounter++
        }
        else { // answer is wrong
            wrongAnswerCounter++
        }
        // write into textViews
        binding.textViewTrue.text = "True : $correctAnswerCounter"
        binding.textViewFalse.text = "True : $wrongAnswerCounter"
    }
}