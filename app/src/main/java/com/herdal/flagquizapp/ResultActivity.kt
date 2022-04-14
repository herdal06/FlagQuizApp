package com.herdal.flagquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.herdal.flagquizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val correctAnswerCounter = intent.getIntExtra("correctAnswerCounter",0)

        binding.textViewResult.text = "$correctAnswerCounter True ${5-correctAnswerCounter} False"
        binding.textViewPercentage.text = "%${(correctAnswerCounter * 100) / 5} Success"
    }

    fun playAgain(view: View) {
        val intent = Intent(this,QuizActivity::class.java)
        startActivity(intent)
        finish()
    }
}