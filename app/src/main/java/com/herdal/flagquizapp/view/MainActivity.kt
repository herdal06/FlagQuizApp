package com.herdal.flagquizapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.herdal.flagquizapp.databinding.ActivityMainBinding
import com.herdal.flagquizapp.service.DatabaseCopyHelper

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        databaseCopy()
    }

    fun startGame(view: View) {
        val intent = Intent(this, QuizActivity::class.java)
        startActivity(intent)
    }

    fun databaseCopy() {
        val copyHelper = DatabaseCopyHelper(this)

        try {
            copyHelper.createDataBase()
            copyHelper.openDataBase()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}