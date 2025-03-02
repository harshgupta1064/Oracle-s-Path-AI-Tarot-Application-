package com.example.aitarotapplication.ui

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aitarotapplication.R
import com.example.aitarotapplication.adapters.resultCardAdapter
import com.example.aitarotapplication.data.Reading
import com.example.aitarotapplication.databinding.ActivityHistoryDetailsBinding
import com.example.aitarotapplication.models.tarotCard

class HistoryDetailsActivity : AppCompatActivity() {
    val binding : ActivityHistoryDetailsBinding by lazy {
        ActivityHistoryDetailsBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        val questionText: TextView = binding.userQuestion
        val recyclerView: RecyclerView = binding.recyclerView

        // Retrieve the Reading object from the intent.
        // Make sure that Reading implements Serializable or Parcelable.
        val reading = intent.getSerializableExtra("READING") as? Reading

        reading?.let {
            questionText.text = it.userQuestion

            // Create a list of tarotCard objects from the reading's card messages.
            // Here, we assign dummy card numbers (1, 2, 3) for display purposes.
            val selectedCards = listOf(
                tarotCard(1, it.card1Message),
                tarotCard(2, it.card2Message),
                tarotCard(3, it.card3Message)
            )


            // Setup the RecyclerView to display the cards horizontally.
            recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = resultCardAdapter(selectedCards)
        }
        binding.chatButton.setOnClickListener{
            val intent = Intent(this,ChatActivity::class.java)
            intent.putExtra("userQuestion", reading?.userQuestion)
            startActivity(intent)

        }

    }
}