package com.example.aitarotapplication.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aitarotapplication.R
import com.example.aitarotapplication.adapters.resultCardAdapter
import com.example.aitarotapplication.databinding.ActivityResultBinding
import com.example.aitarotapplication.models.tarotCard

class ResultActivity : AppCompatActivity() {
    val binding:ActivityResultBinding by lazy {
        ActivityResultBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        // Retrieve selected cards from the Intent.
        // Make sure your tarotCard model implements Parcelable (or Serializable) properly.
        val selectedCards = intent.getSerializableExtra("SELECTED_CARDS") as? ArrayList<tarotCard>
        val userQuestion = intent.getStringExtra("userQuestion")


        // Find the RecyclerView from the layout.
        val recyclerViewResults =   binding.recyclerView

        // Set a horizontal LinearLayoutManager.
        recyclerViewResults.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Set the adapter with the selected cards.
        // If no cards are passed, you might want to handle the null case appropriately.
        selectedCards?.let { cards ->
            recyclerViewResults.adapter = resultCardAdapter(cards)
        }
        binding.userQuestion.text = "$userQuestion"
    }
}