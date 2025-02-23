package com.example.aitarotapplication.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.aitarotapplication.R
import com.example.aitarotapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        val nextButton = binding.nextButton;
        val message = binding.message;
        nextButton.setOnClickListener {
            val userQuestion = message.text.toString().trim()

            if (userQuestion.isNotEmpty()) {
                val intent = Intent(this, TarotCards::class.java)
                intent.putExtra("userQuestion", userQuestion) // Pass question to next screen
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please enter a message.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}