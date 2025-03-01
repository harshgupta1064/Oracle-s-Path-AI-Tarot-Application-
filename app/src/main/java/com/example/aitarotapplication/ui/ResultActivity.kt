package com.example.aitarotapplication.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aitarotapplication.R
import com.example.aitarotapplication.adapters.resultCardAdapter
import com.example.aitarotapplication.data.AppDatabase
import com.example.aitarotapplication.data.Reading
import com.example.aitarotapplication.databinding.ActivityResultBinding
import com.example.aitarotapplication.models.tarotCard
import kotlinx.coroutines.launch

class ResultActivity : AppCompatActivity() {
    val binding:ActivityResultBinding by lazy {
        ActivityResultBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val selectedCards = intent.getSerializableExtra("SELECTED_CARDS") as? ArrayList<tarotCard>
        val userQuestion = intent.getStringExtra("userQuestion")

        // Find the RecyclerView from the layout.
        val recyclerViewResults =   binding.recyclerView

        // Set a horizontal LinearLayoutManager.
        recyclerViewResults.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        selectedCards?.let { cards ->
            recyclerViewResults.adapter = resultCardAdapter(cards)
        }
        binding.userQuestion.text = "$userQuestion"

        if (selectedCards != null && selectedCards.size == 3) {
            // Extract the messages from each selected card
            val reading = userQuestion?.let {
                Reading(
                    userQuestion = it,
                    card1Message = selectedCards[0].cardMessage,
                    card2Message = selectedCards[1].cardMessage,
                    card3Message = selectedCards[2].cardMessage,
                    readingDate = System.currentTimeMillis()
                )
            }

            // Insert the reading into the Room database
            val db = AppDatabase.getDatabase(this)
            lifecycleScope.launch {
                if (reading != null) {
                    db.readingDao().insertReading(reading)
                }
            }
        }
        binding.chatButton.setOnClickListener{
            intent = Intent(this,ChatActivity::class.java)
            intent.putExtra("userQuestion",userQuestion)
            intent.putExtra("card1Message ", selectedCards?.get(0)?.cardMessage)
            intent.putExtra("card2Message ", selectedCards?.get(1)?.cardMessage)
            intent.putExtra("card3Message ", selectedCards?.get(2)?.cardMessage)
            startActivity(intent)
        }

    }
    override fun onBackPressed() {
        super.onBackPressed()
        // Create an intent to start MainActivity
        val intent = Intent(this, MainActivity::class.java)
        // Set flags to clear the activity stack so that MainActivity becomes the top
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish() // Close the current ResultActivity
    }

}