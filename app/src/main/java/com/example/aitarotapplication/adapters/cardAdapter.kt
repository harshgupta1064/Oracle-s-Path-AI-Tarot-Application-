package com.example.aitarotapplication.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aitarotapplication.R
import com.example.aitarotapplication.models.tarotCard



class TarotCardAdapter(
    private val tarotCards: List<tarotCard>,
    private val onSelectionChanged: (List<tarotCard>) -> Unit
) : RecyclerView.Adapter<TarotCardAdapter.ViewHolder>() {

    // List to keep track of selected TarotCards
    private val selectedCards = mutableListOf<tarotCard>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // This TextView displays the sequential card label (e.g., "Card 1")
        val cardNumber : TextView = view.findViewById(R.id.cardNumber)
        fun bind(card: tarotCard, position: Int) {
            // Display the view's sequential number rather than the actual card number.
            cardNumber.text = "${position + 1}"
            // If this card is selected, show it as selected (e.g. half-transparent)
            // If not selected and selection limit is reached, show it as disabled (even lower opacity)
            itemView.alpha = when {
                selectedCards.contains(card) -> 0.8f
                selectedCards.size >= 3 -> 0.3f
                else -> 1.0f
            }

            // Allow clicks if the card is already selected or if fewer than 3 cards are selected.
            itemView.isEnabled = selectedCards.contains(card) || selectedCards.size < 3

            itemView.setOnClickListener {
                if (selectedCards.contains(card)) {
                    // Deselect the card
                    selectedCards.remove(card)
                } else if (selectedCards.size < 3) {
                    // Select the card
                    selectedCards.add(card)
                }
                // Update UI and notify the selection change callback
                notifyDataSetChanged()
                onSelectionChanged(selectedCards.toList())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate your card layout which has a fixed front image and a TextView (cardNumberText)
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // The adapter's list is already shuffled, so binding position corresponds to a shuffled TarotCard.
        val card = tarotCards[position]
        holder.bind(card, position)
    }

    override fun getItemCount(): Int = tarotCards.size
}

