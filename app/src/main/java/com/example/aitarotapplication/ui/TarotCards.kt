package com.example.aitarotapplication.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aitarotapplication.R
import com.example.aitarotapplication.adapters.TarotCardAdapter
import com.example.aitarotapplication.data.TarotCardList
import com.example.aitarotapplication.databinding.ActivityTarotCardsBinding
import com.example.aitarotapplication.models.tarotCard
class TarotCards : AppCompatActivity() {
    val binding : ActivityTarotCardsBinding by lazy {
        ActivityTarotCardsBinding.inflate(layoutInflater)
    }
    private val selectedCards = mutableListOf<tarotCard>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        val userQuestion = intent.getStringExtra("userQuestion")
        val tarotCards = TarotCardList(this)
        val recyclerView = binding.recyclerView
        val confirmButton = binding.confirmButton
//        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = TarotCardAdapter(tarotCards) { selected ->
            selectedCards.clear()
            selectedCards.addAll(selected)
            confirmButton.isEnabled = selectedCards.size == 3
        }

        confirmButton.setOnClickListener {
            if (selectedCards.size == 3) {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("SELECTED_CARDS", ArrayList(selectedCards))
                intent.putExtra("userQuestion",userQuestion)
                startActivity(intent)
            }
        }
    }
}