package com.example.aitarotapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aitarotapplication.R
import com.example.aitarotapplication.models.tarotCard

class resultCardAdapter(
    private val selectedCards: List<tarotCard>
) : RecyclerView.Adapter<resultCardAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardMessageText: TextView = view.findViewById(R.id.resultMessage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_result_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = selectedCards[position]
        holder.cardMessageText.text = card.cardMessage
    }

    override fun getItemCount(): Int = selectedCards.size
}
